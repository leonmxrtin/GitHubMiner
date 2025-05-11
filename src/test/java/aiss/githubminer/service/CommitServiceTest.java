package aiss.githubminer.service;

import aiss.githubminer.model.Commit;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("Get commits of a project")
    void getCommits() {
        String owner = "spring-projects";
        String repo = "spring-framework";
        Integer sinceDays = 4;
        Integer maxPages = 2;
        String expectedId = "33aeb6ee9c6ba2df235ebb09cf9490c92212eee7";
        String expectedTitle = "Deprecate [Property|Preferences]PlaceholderConfigurer for removal";
        List<Commit> commits = commitService.getCommits(owner, repo, sinceDays, maxPages);
        assertNotNull(commits);
        assertFalse(commits.isEmpty());
        assertTrue(commits.stream().anyMatch(commit -> commit.getId().equals(expectedId) && commit.getTitle().equals(expectedTitle)));
        System.out.println(commits);
    }
}