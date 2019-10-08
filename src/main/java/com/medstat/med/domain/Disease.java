package com.medstat.med.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Disease {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NaturalId
    @NonNull
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "diseases")
    private Set<Note> notes;


}
