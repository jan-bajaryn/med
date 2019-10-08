package com.medstat.med.repos;

import com.medstat.med.domain.Drug;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface DrugRepo extends JpaRepository<Drug, Long> {
    @Transactional
    void deleteByName(String name);
}
