package com.medstat.med.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @GetMapping("ha")
    public String ha(){
        return "index";
    }

    @GetMapping({"/","/index"})
    public String red(){
        return "shared/index";
    }

}
