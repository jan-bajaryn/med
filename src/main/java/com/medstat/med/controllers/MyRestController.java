package com.medstat.med.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medstat.med.domain.User;
import com.medstat.med.service.CommentService;
import com.medstat.med.service.LikesService;
import com.medstat.med.service.NoteService;
import com.medstat.med.service.UserService;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/rest")
@Slf4j
public class MyRestController {

    private final CommentService commentService;
    private final UserService userService;
    private final NoteService noteService;
    private final LikesService likesService;

    final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public MyRestController(CommentService commentService,
                            UserService userService,
                            NoteService noteService, LikesService likesService) {
        this.commentService = commentService;
        this.userService = userService;
        this.noteService = noteService;
        this.likesService = likesService;
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
                            @AuthenticationPrincipal User user,
                            @RequestParam(name = "file", required = false) MultipartFile file) throws IOException {

        return noteService.addNoteCascade(user, drugs_json, diseases_json, symptoms_json, comment, name, file);
    }

    @PostMapping("/add_comment")
    public boolean add_comment(@AuthenticationPrincipal User user,
                               @RequestParam(name = "text") String text,
                               @RequestParam(name = "id") Long id) {

        return commentService.addCommentToNote(user, text, id);
    }

    @PostMapping("add_like")
    public int add_like(@AuthenticationPrincipal User user,
                        @RequestParam(name = "id") Long id) {
        return likesService.addUserLike(user, id);
    }

    @GetMapping("is_like_exists")
    public boolean is_like_exists(@AuthenticationPrincipal User user,
                                  @RequestParam(name = "note_id") Long note_id) {
        try {
            return likesService.isLikeExists(user, note_id);
        } catch (Exception e) {
            return false;
        }
    }

    @PostMapping("/delete_note_admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public boolean delete_note_admin(@AuthenticationPrincipal User user,
                                     @RequestParam(name = "path") Long path) {
        return userService.delete_note_admin(user, path);
    }

    @PostMapping("/delete_note_editor")
    @PreAuthorize("hasAuthority('EDITOR')")
    public boolean delete_note_editor(@AuthenticationPrincipal User user,
                                     @RequestParam(name = "path") Long path) {
        return userService.delete_note_editor(user, path);
    }




}
