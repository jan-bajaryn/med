package com.medstat.med.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medstat.med.domain.Disease;
import com.medstat.med.domain.Drug;
import com.medstat.med.domain.Symptom;
import com.medstat.med.domain.User;
import com.medstat.med.repos.DiseaseRepo;
import com.medstat.med.repos.DrugRepo;
import com.medstat.med.repos.SymptomRepo;
import com.medstat.med.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest")
@Slf4j
public class MyRestController {

    @Autowired
    UserService userService;

    @Autowired
    NoteService noteService;

    @Autowired
    DiseaseService diseaseService;
    @Autowired
    SymptomService symptomService;
    @Autowired
    DrugService drugService;

    @Autowired
    DrugRepo drugRepo;
    @Autowired
    DiseaseRepo diseaseRepo;
    @Autowired
    SymptomRepo symptomRepo;

    ObjectMapper mapper = new ObjectMapper();

    @GetMapping
    public List<String> list() {
        return new ArrayList<>(Arrays.asList("haha", "nana"));
    }

    @GetMapping("/k")
    public Map<String, String> map() {
        Map<String, String> map = new HashMap<>();
        map.put("a", "b");
        map.put("d", "b");
        map.put("j", "b");
        return map;
    }

    @PostMapping("/registration")
    public boolean registration(@RequestParam(name = "username") String username,
                                @RequestParam(name = "password") String password) {
        return userService.addUser(username, password);
    }

//    @GetMapping("/registration")
//    public boolean registrationGet(@RequestParam(name = "username") String username,
//                                   @RequestParam(name = "password") String password) {
//        return userService.addUser(username, password);
//    }

    @PostMapping("/my")
    @ResponseBody
    public String something() {
        return "something";
    }

    @GetMapping("/test")
    public boolean test(@RequestParam(name = "myname") String strings,
                        @AuthenticationPrincipal User user) {
        List<String> strings1 = new ArrayList<>();
        try {
            strings1 = mapper.readValue(strings, List.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("list = {}", strings);
        strings1.forEach(System.out::println);
        System.out.println(strings1.get(0));
        log.info("user = {}", user.getUsername());
        return true;
    }

    @PostMapping("/add_note")
    public boolean add_note(@RequestParam(name = "drugs_json") String drugs_json,
                            @RequestParam(name = "diseases_json") String diseases_json,
                            @RequestParam(name = "symptoms_json") String symptoms_json,
                            @RequestParam(name = "comment") String comment,
                            @AuthenticationPrincipal User user) throws IOException {

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
                .map(d -> Drug.builder().name(d).build())
                .peek(drug -> drugRepo.save(drug))
                .collect(Collectors.toSet());

        Set<Disease> diseases_set = diseases.stream()
                .map(d -> Disease.builder().name(d).build())
                .peek(disease -> diseaseRepo.save(disease))
                .collect(Collectors.toSet());

        Set<Symptom> symptoms_set = symptoms.stream()
                .map(s -> Symptom.builder().name(s).build())
                .peek(symptom -> symptomRepo.save(symptom))
                .collect(Collectors.toSet());


        return noteService.addNote(user, comment, drugs_set, symptoms_set, diseases_set);
    }


}
