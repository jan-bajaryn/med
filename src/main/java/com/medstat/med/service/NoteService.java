package com.medstat.med.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medstat.med.domain.*;
import com.medstat.med.repos.DiseaseRepo;
import com.medstat.med.repos.DrugRepo;
import com.medstat.med.repos.NoteRepo;
import com.medstat.med.repos.SymptomRepo;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class NoteService {


    @Value("${upload.path}")
    private String uploadPath;

    private final NoteRepo noteRepo;
    private final ObjectMapper mapper;
    private final DrugRepo drugRepo;
    private final SymptomRepo symptomRepo;
    private final DiseaseRepo diseaseRepo;

    @Autowired
    public NoteService(NoteRepo noteRepo, ObjectMapper mapper, DrugRepo drugRepo, SymptomRepo symptomRepo, DiseaseRepo diseaseRepo) {
        this.noteRepo = noteRepo;
        this.mapper = mapper;
        this.drugRepo = drugRepo;
        this.symptomRepo = symptomRepo;
        this.diseaseRepo = diseaseRepo;
    }

    public boolean addNote(@NonNull User user,
                           @NonNull String name,
                           @NonNull String comment,
                           @NonNull Set<Drug> drugs,
                           @NonNull Set<Symptom> symptoms,
                           @NonNull Set<Disease> diseases,
                           String image) {
        try {
            noteRepo.save(Note.builder()
                    .drugs(drugs)
                    .author(user)
                    .comment(comment)
                    .symptoms(symptoms)
                    .diseases(diseases)
                    .name(name)
                    //check if there will be problem with null
                    .image(image)
                    .build());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean addNoteCascade(User user,
                                  String drugs_json,
                                  String diseases_json,
                                  String symptoms_json,
                                  String comment,
                                  String name,
                                  MultipartFile file) throws IOException {

        log.info("file = {}",file);

        String image = null;

        List<String> drugs = new ArrayList<>();
        List<String> diseases = new ArrayList<>();
        List<String> symptoms = new ArrayList<>();

        drugs = mapper.readValue(drugs_json, List.class);
        diseases = mapper.readValue(diseases_json, List.class);
        symptoms = mapper.readValue(symptoms_json, List.class);

        log.info("drugs = {}", drugs);
        log.info("diseases = {}", diseases);
        log.info("symptoms = {}", symptoms);
        Set<Drug> drugs_set = drugs.stream()
                .map(d -> {
                    Drug drug = drugRepo.findByName(d);
                    return drug == null ? Drug.builder().name(d).build() : drug;
                })
                .peek(drugRepo::save)
                .collect(Collectors.toSet());

        Set<Disease> diseases_set = diseases.stream()
                .map(d -> {
                    Disease disease = diseaseRepo.findByName(d);
                    return disease == null ? Disease.builder().name(d).build() : disease;
                })
                .peek(diseaseRepo::save)
                .collect(Collectors.toSet());

        Set<Symptom> symptoms_set = symptoms.stream()
                .map(s -> {
                    Symptom symptom = symptomRepo.findByName(s);
                    return symptom == null ? Symptom.builder().name(s).build() : symptom;
                })
                .peek(symptomRepo::save)
                .collect(Collectors.toSet());

        if (file != null && !file.getOriginalFilename().isEmpty()) {

            File updoadDir = new File(uploadPath);

            if (!updoadDir.exists()) {
                updoadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + File.separator + resultFileName));
            image = resultFileName;
        }


        return addNote(user, name, comment, drugs_set, symptoms_set, diseases_set,image);

    }
}
