package com.medstat.med.controllers;

import com.medstat.med.domain.Drug;
import com.medstat.med.domain.Note;
import com.medstat.med.repos.DrugRepo;
import com.medstat.med.repos.NoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class WebController {

    @Autowired
    NoteRepo noteRepo;

    @Autowired
    DrugRepo drugRepo;

    @GetMapping
    public String index() {


        Set<Drug> drugs = new HashSet<>();
        Drug drug = new Drug();
        drug.setName("drug1");
        Drug drug2 = new Drug();
        drug2.setName("drug2");

        drugRepo.save(drug);
        drugRepo.save(drug2);

        drugs.add(drug);
        drugs.add(drug2);

        Note note = new Note();
        note.setDrugs(drugs);
        note.setComment("myComment");

        noteRepo.save(note);

        return "index";
    }

    @GetMapping("clear")
    public String clear() {

        List<Note> all = noteRepo.findAll();
//        all.forEach(note ->{
//            note.getDrugs().clear();
//            noteRepo.save(note);
//        });

        for (Note note:
              all) {
            note.getDrugs().clear();
            noteRepo.save(note);
        }

        drugRepo.findAll().forEach(d ->{
            drugRepo.delete(d);
        });

        noteRepo.findAll().forEach(n -> {
            noteRepo.delete(n);
        });

//        drugRepo.findAll().forEach(d ->{
//            drugRepo.delete(d);
//        });

        return "index";
    }


}