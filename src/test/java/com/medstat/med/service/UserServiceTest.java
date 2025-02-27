package com.medstat.med.service;

import com.medstat.med.domain.Role;
import com.medstat.med.domain.User;
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
class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepo userRepo;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void addUser() {
        boolean b = userService.addUser("myusername", "mypassword");
        assertTrue(b);

        User userByName = userRepo.findByUsername("myusername");
        assertEquals("mypassword", userByName.getPassword());
        assertTrue(userByName.getRoles().contains(Role.USER));

        userRepo.deleteByUsername("myusername");
        User userNull = userRepo.findByUsername("myusername");
        assertNull(userNull);
    }


}