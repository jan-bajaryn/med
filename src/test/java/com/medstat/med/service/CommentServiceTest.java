package com.medstat.med.service;

import com.medstat.med.domain.Note;
import com.medstat.med.domain.User;
import com.medstat.med.repos.CommentRepo;
import com.medstat.med.repos.NoteRepo;
import com.medstat.med.repos.UserRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
class CommentServiceTest {

    private static final String USERNAME_TEST = "username1";
    private static final String USER_PASSWORD_TEST = "password1";
    private static final String NOTE_COMMENT_TEST = "this is my comment";
    private static final String COMMENT_TEXT_TEST = "this is very bad post.";

    @Autowired
    NoteService noteService;

    @Autowired
    NoteRepo noteRepo;

    @Autowired
    UserService userService;

    @Autowired
    UserRepo userRepo;

    @Autowired
    CommentService commentService;
    @Autowired
    CommentRepo commentRepo;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addComment() {
        //add user

        userService.addUser(USERNAME_TEST, USER_PASSWORD_TEST);
        User user = userRepo.findByUsername(USERNAME_TEST);

        // add note
        Note note = Note.builder().comment(NOTE_COMMENT_TEST).build();
        noteRepo.save(note);


        //add comment
        boolean b = commentService.addComment(note, user, COMMENT_TEXT_TEST);
        assertTrue(b);


        //delete comment

        commentRepo.deleteByAuthor_Id(user.getId());

        //delete user
        userRepo.deleteByUsername(USERNAME_TEST);
        //delete note
        noteRepo.deleteByComment(note.getComment());

    }
}