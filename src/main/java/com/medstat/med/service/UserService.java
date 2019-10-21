package com.medstat.med.service;


import com.medstat.med.domain.Note;
import com.medstat.med.domain.Role;
import com.medstat.med.domain.User;
import com.medstat.med.repos.NoteRepo;
import com.medstat.med.repos.UserRepo;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.*;

@Service
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;
    private final NoteRepo noteRepo;

    @Autowired
    public UserService(UserRepo userRepo, NoteRepo noteRepo) {
        this.userRepo = userRepo;
        this.noteRepo = noteRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public boolean addUser(@NonNull String username, @NonNull String password) {
        try {
            userRepo.save(User.builder()
                    .active(true)
                    .password(password)
                    .username(username)
                    .roles(new HashSet<>(Arrays.asList(Role.USER)))
                    .build());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getProfileUser(User user, Model model) {
        Optional<User> byId = userRepo.findById(user.getId());
        if (byId.isPresent()) {
            ArrayList<Note> notes = new ArrayList<>(byId.get().getNotes());
            notes.sort(Comparator.comparing(Note::getId));
            model.addAttribute("notes", notes);
        } else
            throw new IllegalArgumentException("There are not note with so id");
        return "profile_user";
    }

    public boolean delete_note_admin(User user, Long path) {
        try {
            noteRepo.deleteById(path);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean delete_note_editor(User user, Long path) {
        Optional<Note> byId = noteRepo.findById(path);
        if (byId.isPresent()) {
            Note note = byId.get();

            if (note.getAuthor() != null && note.getAuthor().getRoles().contains(Role.USER) &&
                    !note.getAuthor().getRoles().contains(Role.ADMIN) &&
                    !note.getAuthor().getRoles().contains(Role.EDITOR)) {
                try{
                    noteRepo.deleteById(path);
                    return true;
                }catch (Exception e){
                    return false;
                }
            }


        }

        return false;
    }

}