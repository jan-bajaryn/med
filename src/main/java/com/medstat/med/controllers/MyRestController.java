package com.medstat.med.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.medstat.med.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/rest")
@Slf4j
public class MyRestController {

    @Autowired
    UserService userService;

    ObjectMapper mapper = new ObjectMapper();

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

//    @GetMapping("/registration")
//    public boolean registrationGet(@RequestParam(name = "username") String username,
//                                   @RequestParam(name = "password") String password) {
//        return userService.addUser(username, password);
//    }

    @PostMapping("/my")
    @ResponseBody
    public String something() {
        return "something";
    }

    @GetMapping("/test")
    public boolean test(@RequestParam(name = "myname") String strings) {
        List<String> strings1 = new ArrayList<>();
        try {
            strings1 = mapper.readValue(strings, List.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("list = {}", strings);
        strings1.forEach(System.out::println);
        System.out.println(strings1.get(0));
        return true;
    }


}
