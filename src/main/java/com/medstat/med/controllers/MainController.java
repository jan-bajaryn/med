package com.medstat.med.controllers;

import com.medstat.med.domain.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping({"/","/index"})
    public String red(){
        return "shared/index";
    }

    @GetMapping("/profile_user")
    public String profile_user(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("notes", user.getNotes());
        return "profile_user";
    }
}
