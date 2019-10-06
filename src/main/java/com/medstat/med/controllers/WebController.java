package com.medstat.med.controllers;

import com.medstat.med.domain.Note;
import com.medstat.med.repos.NoteRepo;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@Setter
@Slf4j
public class WebController {

    NoteRepo noteRepo;
//    DiseaseRepo diseaseRepo;
//    SymptomRepo symptomRepo;

    private int count;

    public WebController(@Autowired NoteRepo noteRepo
//                         @Autowired DiseaseRepo diseaseRepo,
//                         @Autowired SymptomRepo symptomRepo
    ) {
        this.noteRepo = noteRepo;
//        this.diseaseRepo = diseaseRepo;
//        this.symptomRepo = symptomRepo;
    }

    @GetMapping
    public String index() {
        log.info("count = {}", count);
//
//        Set<Drug> drugs = new HashSet<>();
//        Drug drug = new Drug();
//        drug.setName("drug1");
//        Drug drug2 = new Drug();
//        drug2.setName("drug2");
//
//        drugRepo.save(drug);
//        drugRepo.save(drug2);
//
//        drugs.add(drug);
//        drugs.add(drug2);
//
//        Note note = new Note();
//        note.setDrugs(drugs);
//        note.setComment("myComment");
//
//        noteRepo.save(note);

        return "index";
    }

    @GetMapping("clear")
    public String clear() {

        List<Note> all = noteRepo.findAll();

//        all.forEach(note ->{
//            note.getDrugs().clear();
//            noteRepo.save(note);
//        });



        noteRepo.findAll().forEach(n -> {
            noteRepo.delete(n);
        });

//        drugRepo.findAll().forEach(d ->{
//            drugRepo.delete(d);
//        });

        return "index";
    }


}