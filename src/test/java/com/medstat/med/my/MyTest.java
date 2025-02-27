package com.medstat.med.my;

import com.medstat.med.domain.Role;
import com.medstat.med.domain.User;
import com.medstat.med.repos.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class MyTest {

    @Autowired
    UserRepo userRepo;


    @Test
    void addFirstAdmin() {
        User user = userRepo.save(User.builder()
                .username("a")
                .password("a")
                .active(true)
                .roles(new HashSet<>(Arrays.asList(Role.USER, Role.ADMIN)))
                .build());
        assertNotNull(user);

    }

    @Test
    void deleteUserMy(){
        userRepo.deleteByUsername("test_username");
        User user = userRepo.findByUsername("test_username");
        assertNull(user);

    }

    @Test
    void unique_user(){
        User user = userRepo.save(User.builder().username("haha").password("haha").active(true).build());
        assertNotNull(user);
    }

}
