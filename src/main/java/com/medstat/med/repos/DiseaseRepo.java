package com.medstat.med.repos;

import com.medstat.med.domain.Disease;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface DiseaseRepo extends JpaRepository<Disease, Long> {
    @Transactional
    void deleteByName(String name);
}
