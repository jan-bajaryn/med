package com.medstat.med.domain;

import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Drug {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NaturalId
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "drugs"/*, cascade = CascadeType.ALL*/)
    private Set<Note> notes;
}
