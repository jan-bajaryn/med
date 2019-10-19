package com.medstat.med.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medstat.med.domain.User;
import com.medstat.med.service.CommentService;
import com.medstat.med.service.NoteService;
import com.medstat.med.service.UserService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/rest")
@Slf4j
public class MyRestController {

    private final CommentService commentService;
    private final UserService userService;
    private final NoteService noteService;

    final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public MyRestController(CommentService commentService,
                            UserService userService,
                            NoteService noteService) {
        this.commentService = commentService;
        this.userService = userService;
        this.noteService = noteService;
    }

    @PostMapping("/registration")
    public boolean registration(@RequestParam(name = "username") String username,
                                @RequestParam(name = "password") String password) {
        return userService.addUser(username, password);
    }


    @PostMapping("/add_note")
    public boolean add_note(@RequestParam(name = "drugs_json") String drugs_json,
                            @RequestParam(name = "diseases_json") String diseases_json,
                            @RequestParam(name = "symptoms_json") String symptoms_json,
                            @RequestParam(name = "comment") String comment,
                            @NonNull @RequestParam(name = "name") String name,
                            @AuthenticationPrincipal User user) throws IOException {

        return noteService.addNoteCascade(user, drugs_json, diseases_json, symptoms_json, comment, name);
    }

    @PostMapping("/add_comment")
    public boolean add_comment(@AuthenticationPrincipal User user,
                               @RequestParam(name = "text") String text,
                               @RequestParam(name = "id") Long id) {

        return commentService.addCommentToNote(user,text,id);
    }


}
