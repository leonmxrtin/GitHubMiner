package aiss.githubminer.service;

import aiss.githubminer.model.Commit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommitServiceTest {

    @Autowired
    CommitService commitService;

    @Test
    void getCommits() {
        String owner = "spring-projects";
        String repo = "spring-framework";
        Integer sinceDays = 4;
        Integer maxPages = 2;
        List<Commit> commits = commitService.getCommits(owner, repo, sinceDays, maxPages);
        assertNotNull(commits);
        System.out.println(commits);
    }
}