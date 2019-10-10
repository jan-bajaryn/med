package com.medstat.med.domain;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "mylike")
@Data
public class Mylike {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "note_id")
    private Note note;

}
