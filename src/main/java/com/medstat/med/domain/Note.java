package com.medstat.med.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToMany(fetch = FetchType.EAGER/*, cascade = CascadeType.ALL*/)
    @JoinTable(
            name = "note_drug",
            joinColumns = {@JoinColumn(name = "note_id")},
            inverseJoinColumns = {@JoinColumn(name = "drug_id")}
    )
    private Set<Drug> drugs;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    private String comment;

}
