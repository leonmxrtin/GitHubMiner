package aiss.githubminer.service;

import aiss.githubminer.model.Commit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

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
        Integer sinceDays = 3650;
        Integer maxPages = 2;

        String expectedCommitId = "33aeb6ee9c6ba2df235ebb09cf9490c92212eee7";
        String expectedCommitWebUrl = "https://github.com/spring-projects/spring-framework/commit/33aeb6ee9c6ba2df235ebb09cf9490c92212eee7";
        String expectedCommitAuthorName = "Sam Brannen";
        String expectedCommitAuthorEmail = "104798+sbrannen@users.noreply.github.com";
        String expectedCommitAuthoredDate = "2025-05-11T15:01:04Z";
        String expectedCommitTitle = "Deprecate [Property|Preferences]PlaceholderConfigurer for removal";
        String expectedCommitMessage = """
                Deprecate [Property|Preferences]PlaceholderConfigurer for removal
                
                PropertyPlaceholderConfigurer and PreferencesPlaceholderConfigurer have
                been officially deprecated since Spring Framework 5.2.
                
                Since we no longer expect applications to depend on these outdated
                mechanisms, this commit deprecates these classes "for removal" in
                Spring Framework 8.0.
                
                Closes gh-34880""";

        List<Commit> commits = commitService.getCommits(owner, repo, sinceDays, maxPages);
        assertNotNull(commits);
        assertFalse(commits.isEmpty());

        Optional<Commit> optCommit = commits.stream().filter(c -> c.getId().equals(expectedCommitId)).findFirst();
        assertTrue(optCommit.isPresent());

        Commit commit = optCommit.get();
        assertEquals(expectedCommitId, commit.getId());
        assertEquals(expectedCommitWebUrl, commit.getWebUrl());
        assertEquals(expectedCommitAuthorName, commit.getAuthorName());
        assertEquals(expectedCommitAuthorEmail, commit.getAuthorEmail());
        assertEquals(expectedCommitAuthoredDate, commit.getAuthoredDate());
        assertEquals(expectedCommitTitle, commit.getTitle());
        assertEquals(expectedCommitMessage, commit.getMessage());

        System.out.println(commits);
    }
}