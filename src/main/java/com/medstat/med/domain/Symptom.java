package com.medstat.med.domain;

import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Symptom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NaturalId
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "symptoms")
    private Set<Note> notes;
}
