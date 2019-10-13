package com.medstat.med.service;

import com.medstat.med.repos.SymptomRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class SymptomServiceTest {

    public static final String SYMPTOM_TEST ="symptom_test";

    @Autowired
    SymptomRepo symptomRepo;
    @Autowired
    SymptomService symptomService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addSymptom() {

        boolean b = symptomService.addSymptom(SYMPTOM_TEST);
        assertTrue(b);
        symptomRepo.deleteByName(SYMPTOM_TEST);
    }
}