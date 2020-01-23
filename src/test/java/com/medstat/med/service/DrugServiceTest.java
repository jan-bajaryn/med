package com.medstat.med.service;

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
class DrugServiceTest {

    private static final String DRUG_TEST = "mydrug";

//    @Autowired
//    DrugService drugService;

//    @Autowired
//    DrugRepo drugRepo;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addDrug() {
//        boolean b = drugService.addDrug(DRUG_TEST);
//        assertTrue(b);

//        drugRepo.deleteByName(DRUG_TEST);

    }

}