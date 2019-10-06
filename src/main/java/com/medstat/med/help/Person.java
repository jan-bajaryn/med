package com.medstat.med.help;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Person {
    private String name;
    private String surname;


    public String myMeth() {
        return name + " haha " + surname;
    }
}
