package com.medstat.med.service;

import com.medstat.med.domain.Disease;
import com.medstat.med.repos.DiseaseRepo;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiseaseService {

    @Autowired
    DiseaseRepo diseaseRepo;

    public boolean addDisease(@NonNull String name) {
        try {
            diseaseRepo.save(Disease.builder().name(name).build());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
