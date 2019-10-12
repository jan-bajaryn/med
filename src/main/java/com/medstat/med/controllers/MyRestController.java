package com.medstat.med.controllers;

import com.medstat.med.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/rest")
public class MyRestController {

    @Autowired
    UserService userService;

    @GetMapping
    public List<String> list() {
        return new ArrayList<>(Arrays.asList("haha", "nana"));
    }

    @GetMapping("/k")
    public Map<String, String> map() {
        Map<String, String> map = new HashMap<>();
        map.put("a", "b");
        map.put("d", "b");
        map.put("j", "b");
        return map;
    }

    @PostMapping("/registration")
    public boolean registration(@RequestParam(name = "username") String username,
                                @RequestParam(name = "password") String password) {
        return userService.addUser(username, password);
    }

    @GetMapping("/registration")
    public boolean registrationGet(@RequestParam(name = "username") String username,
                                   @RequestParam(name = "password") String password) {
        return userService.addUser(username, password);
    }

    @PostMapping("/my")
    @ResponseBody
    public String something(){
        return "something";
    }


}
