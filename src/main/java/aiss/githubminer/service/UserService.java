package aiss.githubminer.service;

import aiss.githubminer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    @Autowired
    RestTemplate restGitHub;

    @Cacheable("users")
    public User getUser(String username) {
        System.out.println("Getting user " + username);
        return restGitHub.getForObject("/users/" + username, User.class);
    }
}
