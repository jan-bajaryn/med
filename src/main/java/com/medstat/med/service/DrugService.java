package com.medstat.med.service;

import com.medstat.med.domain.Drug;
import com.medstat.med.repos.DrugRepo;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DrugService {

    private final DrugRepo drugRepo;

    @Autowired
    public DrugService(DrugRepo drugRepo) {
        this.drugRepo = drugRepo;
    }

    public boolean addDrug(@NonNull String name){
        try{
            drugRepo.save(Drug.builder().name(name).build());
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
