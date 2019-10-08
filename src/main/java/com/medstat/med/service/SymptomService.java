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

    @Autowired
    SymptomRepo symptomRepo;

    public boolean addSymptom(@NonNull String name){
        try{
            symptomRepo.save(new Symptom(name));
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
