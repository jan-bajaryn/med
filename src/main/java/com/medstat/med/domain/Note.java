package com.medstat.med.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "note_symptom",
            joinColumns = {@JoinColumn(name = "note_id")},
            inverseJoinColumns = {@JoinColumn(name = "symptom_id")}
    )
    private Set<Symptom> symptoms;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "note_disease",
            joinColumns = {@JoinColumn(name = "note_id")},
            inverseJoinColumns = {@JoinColumn(name = "disease_id")}
    )
    private Set<Disease> diseases;

    private String comment;


}
