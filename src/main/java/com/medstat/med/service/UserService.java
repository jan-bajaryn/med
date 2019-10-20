package com.medstat.med.service;


import com.medstat.med.domain.Note;
import com.medstat.med.domain.Role;
import com.medstat.med.domain.User;
import com.medstat.med.repos.UserRepo;
import lombok.NonNull;
import org.aspectj.weaver.ast.Not;
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

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
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
}