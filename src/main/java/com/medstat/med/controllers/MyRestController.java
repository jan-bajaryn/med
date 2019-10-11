package com.medstat.med.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/rest")
public class MyRestController {

    @GetMapping
    public List<String> list() {
        return new ArrayList<>(Arrays.asList("haha", "nana"));
    }

    @GetMapping("/k")
    public Map<String, String> map(){
        Map<String ,String > map = new HashMap<>();
        map.put("a", "b");
        map.put("d", "b");
        map.put("j", "b");
        return map;
    }


}
