package com.medstat.med.domain;

import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Disease {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NaturalId
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "diseases")
    private Set<Note> notes;


}
