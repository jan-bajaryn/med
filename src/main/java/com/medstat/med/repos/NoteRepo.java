package com.medstat.med.repos;

import com.medstat.med.domain.Disease;
import com.medstat.med.domain.Drug;
import com.medstat.med.domain.Note;
import com.medstat.med.domain.Symptom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepo extends JpaRepository<Note, Long> {
//    List<Note> findByDrug(Drug drug);

//    List<Note> findBySymptom(Symptom symptom);

//    List<Note> findByDisease(Disease disease);
}
