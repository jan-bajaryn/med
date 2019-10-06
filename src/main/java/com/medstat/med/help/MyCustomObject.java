package com.medstat.med.help;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MyCustomObject {
    private String name;
    private List<Person> persons;
}