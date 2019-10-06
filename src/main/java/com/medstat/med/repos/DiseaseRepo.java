package com.medstat.med.repos;

import com.medstat.med.domain.Disease;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiseaseRepo extends JpaRepository<Disease, Long> {
}
