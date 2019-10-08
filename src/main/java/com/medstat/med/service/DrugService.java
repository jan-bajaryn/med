package com.medstat.med.service;

import com.medstat.med.domain.Drug;
import com.medstat.med.repos.DrugRepo;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DrugService {

    @Autowired
    DrugRepo drugRepo;

    public boolean addDrug(@NonNull String name){
        try{
            drugRepo.save(new Drug(name));
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
