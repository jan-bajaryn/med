package com.medstat.med.domain;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "myelement")
@Data
public class MyElement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String mydata;

    private String email;

}
