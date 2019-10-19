package com.medstat.med.controllers;

import com.medstat.med.domain.Note;
import com.medstat.med.repos.NoteRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
@Slf4j
@RequestMapping("/shared")
public class SharedController {

    private final NoteRepo noteRepo;

    @Autowired
    public SharedController(NoteRepo noteRepo) {
        this.noteRepo = noteRepo;
    }

    @GetMapping
    public String index() {
        return "shared/index";
    }

    @GetMapping("note_page/{id}")
    public String note_page(@PathVariable Long id,
                            Model model) {
        Optional<Note> byId = noteRepo.findById(id);
        byId.ifPresent(note -> model.addAttribute("note", note));
        return "shared/note_page";
    }

    @GetMapping("/search")
    public String search(Model model) {
        model.addAttribute("notes", noteRepo.findAll());
        return "shared/search";
    }

}