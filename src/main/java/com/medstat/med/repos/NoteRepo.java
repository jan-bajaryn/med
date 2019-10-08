package com.medstat.med.repos;

import com.medstat.med.domain.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface NoteRepo extends JpaRepository<Note, Long> {

    @Transactional
    void deleteByComment(String commentTest);

    List<Note> findByComment(String comment);
//    List<Note> findByDrug(Drug drug);

//    List<Note> findBySymptom(Symptom symptom);

//    List<Note> findByDisease(Disease disease);
}
