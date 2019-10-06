package com.medstat.med.domain;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "mylikes")
@Data
public class MyLikes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

}
