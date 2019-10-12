package com.medstat.med.service;

import com.medstat.med.domain.Disease;
import com.medstat.med.repos.DiseaseRepo;
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
class DiseaseServiceTest {

    public static final String DISEASE_TEST = "disease1";

    @Autowired
    DiseaseService diseaseService;

    @Autowired
    DiseaseRepo diseaseRepo;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addDisease() {
        boolean b = diseaseService.addDisease(DISEASE_TEST);
        assertTrue(b);
        diseaseRepo.deleteByName(DISEASE_TEST);

    }

}