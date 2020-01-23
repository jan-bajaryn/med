package com.medstat.med.controllers;

import com.medstat.med.domain.*;
import com.medstat.med.repos.NoteRepo;
import com.medstat.med.repos.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;


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
    public String note_page(@PathVariable String id,
                            Model model,
                            //user added
                            @AuthenticationPrincipal User user) {
        Optional<Note> byId = noteRepo.findById(id);
        if (byId.isPresent()) {
            Note note = byId.get();

            if (note.getComments() == null) {
                note.setComments(new ArrayList<>());
            } else {
                log.info("comment={}", note.getComments());
                note.setComments(note.getComments().stream().filter(Objects::nonNull).collect(Collectors.toList()));
                note.getComments().sort(Comparator.comparing(Comment::getCreatedDate));
            }

            Map<Comment, String> collect = note.getComments().stream()
                    .collect(Collectors.toMap(e -> e, e -> userRepo.findById(e.getAuthorId()).get().getUsername()));
            model.addAttribute("note", note);
            model.addAttribute("comments", collect.entrySet());

            //working with user
            if (user != null) {
                Optional<User> byIdUser = userRepo.findById(user.getId());
                if (byIdUser.isPresent()) {
                    user = byIdUser.get();

                    if (user.getRoles().contains(Role.ADMIN))
                        model.addAttribute("role", "admin");
                    else if (user.getRoles().contains(Role.EDITOR))
                        model.addAttribute("role", "editor");

                }
            }
            // end working with user


        }
        return "shared/note_page";
    }

    @GetMapping("/search")
    public String search(Model model) {
        Map<Note, String> collect = toNoteUsernameMap((List<Note>) noteRepo.findAll());
        model.addAttribute("notes", collect.entrySet());
        return "shared/search";
    }

    private Map<Note, String> toNoteUsernameMap(List<Note> notes) {
        return (notes).stream()
                .collect(Collectors.toMap(e -> e, e -> userRepo.findById(e.getAuthorId()).get().getUsername()));
    }

    @GetMapping("/filter")
    public String filter(Model model,
                         @RequestParam(required = false, name = "filter") String filter) {
        if (filter == null || filter.isEmpty())
            return "redirect:/shared/search";

        List<Note> notes = ((List<Note>) noteRepo.findAll()).stream()
                .filter(note -> {
                    List<String> noteNames = note.getDiseases().stream()
                            .map(Disease::getName)
                            .collect(Collectors.toList());
                    for (String name : noteNames) {
                        if (name.contains(filter)) {
                            return true;
                        }
                    }
                    return false;
                })
                .collect(Collectors.toList());
        Map<Note, String> collect = toNoteUsernameMap(notes);

        model.addAttribute("notes", collect.entrySet());
        return "shared/search";
    }

}