package com.medstat.med.service;

import com.medstat.med.domain.*;
import com.medstat.med.repos.NoteRepo;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class NoteService {

    @Autowired
    NoteRepo noteRepo;

    public boolean addNote(@NonNull User user,
                           @NonNull String comment,
                           @NonNull Set<Drug> drugs,
                           @NonNull Set<Symptom> symptoms,
                           @NonNull Set<Disease> diseases){
        Note note = new Note();

        note.setDrugs(drugs);
        note.setAuthor(user);
        note.setComment(comment);
        note.setSymptoms(symptoms);
        note.setDiseases(diseases);
        try{
            noteRepo.save(note);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
