package com.medstat.med.repos;

import com.medstat.med.domain.Symptom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SymptomRepo extends JpaRepository<Symptom, Long> {
}
