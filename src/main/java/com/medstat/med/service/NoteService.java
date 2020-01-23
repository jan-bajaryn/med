package com.medstat.med.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medstat.med.domain.*;
import com.medstat.med.repos.NoteRepo;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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

    @Autowired
    public NoteService(NoteRepo noteRepo, ObjectMapper mapper) {
        this.noteRepo = noteRepo;
        this.mapper = mapper;
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
                    .authorId(user.getId())
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
                                  String drugsJson,
                                  String diseasesJson,
                                  String symptomsJson,
                                  String comment,
                                  String name,
                                  MultipartFile file) throws IOException {

        log.info("file = {}", file);

        String image = null;

        List<String> drugs;
        List<String> diseases;
        List<String> symptoms;

        drugs = mapper.readValue(drugsJson, List.class);
        diseases = mapper.readValue(diseasesJson, List.class);
        symptoms = mapper.readValue(symptomsJson, List.class);

        log.info("drugs = {}", drugs);
        log.info("diseases = {}", diseases);
        log.info("symptoms = {}", symptoms);
        Set<Drug> drugSet = drugs.stream()
                .map(d -> Drug.builder().name(d).build())
                .collect(Collectors.toSet());

        Set<Disease> diseasesSet = diseases.stream()
                .map(d -> Disease.builder().name(d).build())
                .collect(Collectors.toSet());

        Set<Symptom> symptomsSet = symptoms.stream()
                .map(s -> Symptom.builder().name(s).build())
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


        return addNote(user, name, comment, drugSet, symptomsSet, diseasesSet, image);

    }
}
