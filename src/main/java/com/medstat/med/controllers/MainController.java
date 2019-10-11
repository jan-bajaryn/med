package com.medstat.med.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("ha")
    public String ha(){
        return "index";
    }
}
