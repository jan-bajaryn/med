package com.medstat.med.service;

import com.medstat.med.domain.Drug;
import com.medstat.med.domain.Symptom;
import com.medstat.med.repos.DrugRepo;
import com.medstat.med.repos.SymptomRepo;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SymptomService {

    private final SymptomRepo symptomRepo;

    @Autowired
    public SymptomService(SymptomRepo symptomRepo) {
        this.symptomRepo = symptomRepo;
    }

    public boolean addSymptom(@NonNull String name){
        try{
            symptomRepo.save(Symptom.builder().name(name).build());
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
