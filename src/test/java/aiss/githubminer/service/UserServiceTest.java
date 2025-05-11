package aiss.githubminer.service;

import aiss.githubminer.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    void getUser() {
        User user = userService.getUser("reda-alaoui");
        assertNotNull(user);
        System.out.println(user);
    }
}