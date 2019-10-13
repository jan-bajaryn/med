package com.medstat.med.service;

import com.medstat.med.domain.*;
import com.medstat.med.repos.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class NoteServiceTest {
    private static final String USERNAME_TEST = "username_for_my_test_very_important";
    private static final String PASSWORD_TEST = "password_for_my_test_very_important";
    private static final boolean ACTIVE_TEST = true;

    private static final String DRUG_TEST = "drug_for_my_test_very_important";
    private static final String SYMPTOM_TEST = "symptom_for_my_test_very_important";
    private static final String DISEASE_TEST = "disease_for_my_test_very_important";

    @Autowired
    UserService userService;
    @Autowired
    DiseaseService diseaseService;
    @Autowired
    DrugService drugService;
    @Autowired
    NoteService noteService;
    @Autowired
    SymptomService symptomService;

    @Autowired
    DiseaseRepo diseaseRepo;
    @Autowired
    DrugRepo drugRepo;
    @Autowired
    NoteRepo noteRepo;
    @Autowired
    SymptomRepo symptomRepo;
    @Autowired
    UserRepo userRepo;


    private static Set<Drug> DRUGS;
    private static Set<Symptom> SYMPTOMS;
    private static Set<Disease> DISEASES;
    private static final String COMMENT_TEST = "comment1";

    private User USER;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {

    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {


//        noteRepo.deleteByComment(COMMENT_TEST);
    }

    @org.junit.jupiter.api.Test
    void addNote() {

        //before
        USER = userRepo.save(User.builder().username(USERNAME_TEST)
                .password(PASSWORD_TEST)
                .active(ACTIVE_TEST)
                .build());
        Drug drug = new Drug(DRUG_TEST);
        drugRepo.save(drug);
        DRUGS = new HashSet<>(Arrays.asList(drug));


        Symptom symptom = new Symptom(SYMPTOM_TEST);
        symptomRepo.save(symptom);
        SYMPTOMS = new HashSet<>(Arrays.asList(symptom));


        Disease disease = new Disease(DISEASE_TEST);
        diseaseRepo.save(disease);
        DISEASES = new HashSet<>(Arrays.asList(disease));
        // after before

        boolean b = noteService.addNote(USER, COMMENT_TEST, DRUGS, SYMPTOMS, DISEASES);
        assertTrue(b);

        //after
        //здесь могут быть проблемы
        Note note = noteRepo.findByComment(COMMENT_TEST).get(0);
        note.getDrugs().clear();
        note.getDiseases().clear();
        note.getSymptoms().clear();
        noteRepo.save(note);
        noteRepo.delete(note);

        userRepo.deleteByUsername(USERNAME_TEST);
        drugRepo.deleteByName(DRUG_TEST);
        symptomRepo.deleteByName(SYMPTOM_TEST);
        diseaseRepo.deleteByName(DISEASE_TEST);
        //after after
    }
}