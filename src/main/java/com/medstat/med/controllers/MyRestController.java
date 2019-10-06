package com.medstat.med.controllers;

import com.medstat.med.help.MyCustomObject;
import com.medstat.med.help.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class MyRestController {

    @GetMapping
    public String myMeth() {
        return "Govno";
    }

    @GetMapping("/list")
    public List<String> list() {
        List<String> list = new ArrayList<>();
        list.add("govno");
        list.add("mocha");
        list.add("dermo");
        return list;
    }

    @GetMapping("/two")
    public MyCustomObject custom() {
        ArrayList<Person> persons = new ArrayList<>(Arrays.asList(new Person("name1", "surname1"),
                new Person("name2", "surname2"),
                new Person("name3", "surname3"))
        );

        return new MyCustomObject("myname", persons);
    }
}
