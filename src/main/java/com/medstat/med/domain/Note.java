package com.medstat.med.domain;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.*;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Note {

    @Id
    private String id;

    private String authorId;
    private String name;
    private Set<Drug> drugs;
    private Set<Symptom> symptoms;
    private Set<Disease> diseases;
    private Set<Mylike> mylikes;
    private List<Comment> comments;
    private String comment;
    private String image;

    {
        drugs = new LinkedHashSet<>();
        symptoms = new LinkedHashSet<>();
        diseases = new LinkedHashSet<>();
        mylikes = new LinkedHashSet<>();
        comments = new ArrayList<>();
    }

}
