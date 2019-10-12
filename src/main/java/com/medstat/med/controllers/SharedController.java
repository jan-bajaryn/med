package com.medstat.med.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@Slf4j
@RequestMapping("/shared")
public class SharedController {


    @GetMapping
    public String index() {
        return "noteindex";
    }

    @GetMapping("clear")
    public String clear() {

        return "noteindex";
    }


    @PostMapping("/haha")
    public String something(@RequestParam(name = "text", required = false) String text){
        return "shared/note_page";
    }

//    @GetMapping("/login")
//    public String login(){
//        return "login";
//    }


}