package com.medstat.med.domain;

import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Drug {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NaturalId
    @NonNull
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "drugs"/*, cascade = CascadeType.ALL*/)
    private Set<Note> notes;
}
