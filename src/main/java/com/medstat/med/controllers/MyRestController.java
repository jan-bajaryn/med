package com.medstat.med.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/rest")
public class MyRestController {

    @GetMapping
    public Map<Integer, String> rest() {
        Map<Integer, String> map = new HashMap<>();
        map.put(4, "haha");
        map.put(5, "haha1");
        map.put(2, "haha2");
        return map;
    }
}
