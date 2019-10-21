package com.medstat.med.controllers;

import com.medstat.med.domain.Comment;
import com.medstat.med.domain.Note;
import com.medstat.med.domain.Role;
import com.medstat.med.domain.User;
import com.medstat.med.repos.NoteRepo;
import com.medstat.med.repos.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.Optional;


@Controller
@Slf4j
@RequestMapping("/shared")
public class SharedController {

    private final NoteRepo noteRepo;
    private final UserRepo userRepo;

    @Autowired
    public SharedController(NoteRepo noteRepo, UserRepo userRepo) {
        this.noteRepo = noteRepo;
        this.userRepo = userRepo;
    }

    @GetMapping
    public String index() {
        return "shared/index";
    }

    @GetMapping("note_page/{id}")
    public String note_page(@PathVariable Long id,
                            Model model,
                            //user added
                            @AuthenticationPrincipal User user) {
        Optional<Note> byId = noteRepo.findById(id);
        if (byId.isPresent()) {
            Note note = byId.get();
            note.getComments().sort(Comparator.comparing(Comment::getId));
            model.addAttribute("note", note);
            //working with user
            if (user!=null){
                Optional<User> byIdUser = userRepo.findById(user.getId());
                if (byIdUser.isPresent()){
                    user = byIdUser.get();

                    if (user.getRoles().contains(Role.ADMIN))
                        model.addAttribute("role","admin");
                    else if (user.getRoles().contains(Role.EDITOR))
                        model.addAttribute("role","editor");

                }
            }
            // end working with user


        }
        return "shared/note_page";
    }

    @GetMapping("/search")
    public String search(Model model) {
        model.addAttribute("notes", noteRepo.findAll());
        return "shared/search";
    }

}