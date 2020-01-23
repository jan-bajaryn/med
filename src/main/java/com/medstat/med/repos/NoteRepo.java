package com.medstat.med.repos;

import com.medstat.med.domain.Note;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NoteRepo extends CrudRepository<Note, String> {

    @Transactional
    void deleteByComment(String commentTest);

    List<Note> findByComment(String comment);

    List<Note> findAllByAuthorId(String id);
//    List<Note> findByDrug(Drug drug);

//    List<Note> findBySymptom(Symptom symptom);

//    List<Note> findByDisease(Disease disease);
}
