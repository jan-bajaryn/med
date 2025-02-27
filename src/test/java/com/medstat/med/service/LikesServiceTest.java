package com.medstat.med.service;

import com.medstat.med.domain.Mylike;
import com.medstat.med.domain.Note;
import com.medstat.med.domain.User;
import com.medstat.med.repos.MyLikeRepo;
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
class LikesServiceTest {

    public static final String USER_TEST = "myusername";
    public static final String PASSWORD_TEST = "mypassword";
    public static final String NOTE_TEST = "my comment is here";
    public static final String NOTE_NAME_TEST = "my_name_test_hahaha";
    @Autowired
    LikesService likesService;
    @Autowired
    MyLikeRepo myLikeRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    UserService userService;
    @Autowired
    NoteRepo noteRepo;
    @Autowired
    NoteService noteService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addLike() {
        //creating user
        userService.addUser(USER_TEST, PASSWORD_TEST);
        User user = userRepo.findByUsername(USER_TEST);

        //доработать здесь обязательно
        Note note = Note.builder().comment(NOTE_TEST).name(NOTE_NAME_TEST).build();
        noteRepo.save(note);

        boolean b = likesService.addLike(user, note);
        assertTrue(b);

        Mylike mylike = myLikeRepo.findByAuthor_Id(user.getId());

        assertNotNull(mylike);

        myLikeRepo.delete(mylike);

        userRepo.deleteByUsername(USER_TEST);
        noteRepo.deleteByComment(NOTE_TEST);

    }
}