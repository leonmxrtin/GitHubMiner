package aiss.githubminer.service;

import aiss.githubminer.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    @DisplayName("Get a user")
    void getUser() {
        String username = "reda-alaoui";

        String expectedUserId = "2890843";
        String expectedUserName = "Réda Housni Alaoui";
        String expectedUserAvatarUrl = "https://avatars.githubusercontent.com/u/2890843?v=4";
        String expectedUserWebUrl = "https://github.com/reda-alaoui";


        User user = userService.getUser(username);
        assertNotNull(user);

        assertEquals(username, user.getUsername());
        assertEquals(expectedUserId, user.getId());
        assertEquals(expectedUserName, user.getName());
        assertEquals(expectedUserAvatarUrl, user.getAvatarUrl());
        assertEquals(expectedUserWebUrl, user.getWebUrl());

        System.out.println(user);
    }
}