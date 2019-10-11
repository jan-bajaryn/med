package com.medstat.med.domain;

import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@RequiredArgsConstructor
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Disease {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NaturalId
    @NonNull
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "diseases")
    private Set<Note> notes;


}
