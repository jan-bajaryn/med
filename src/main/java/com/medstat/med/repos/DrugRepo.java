package com.medstat.med.repos;

import com.medstat.med.domain.Drug;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugRepo extends JpaRepository<Drug, Long> {
}
