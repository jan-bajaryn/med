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
            joinColumns = { @JoinColumn(name = "note_id") },
            inverseJoinColumns = { @JoinColumn(name = "drug_id") }
    )
    private Set<Drug> drugs;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "symptom_id")
//    private List<Symptom> symptom;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "disease_id")
//    private List<Disease> disease;

    private String comment;

}
