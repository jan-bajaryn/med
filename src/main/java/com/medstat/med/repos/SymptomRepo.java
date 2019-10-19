package com.medstat.med.repos;

import com.medstat.med.domain.Symptom;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface SymptomRepo extends JpaRepository<Symptom, Long> {

    @Transactional
    void deleteByName(String name);

    Symptom findByName(String symptom);
}
