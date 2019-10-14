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
                           @NonNull String name,
                           @NonNull String comment,
                           @NonNull Set<Drug> drugs,
                           @NonNull Set<Symptom> symptoms,
                           @NonNull Set<Disease> diseases) {
        try {
            noteRepo.save(Note.builder()
                    .drugs(drugs)
                    .author(user)
                    .comment(comment)
                    .symptoms(symptoms)
                    .diseases(diseases)
                    .name(name)
                    .build());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
