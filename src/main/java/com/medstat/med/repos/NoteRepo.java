package com.medstat.med.repos;

import com.medstat.med.domain.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepo extends JpaRepository<Note, Long> {
//    List<Note> findByDrug(Drug drug);

//    List<Note> findBySymptom(Symptom symptom);

//    List<Note> findByDisease(Disease disease);
}
