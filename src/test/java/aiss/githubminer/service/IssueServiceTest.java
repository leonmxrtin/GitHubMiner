package aiss.githubminer.service;

import aiss.githubminer.model.Issue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IssueServiceTest {

    @Autowired
    IssueService issueService;

    @Test
    @DisplayName("Get all issues of a project")
    void getIssues() {
        String owner = "spring-projects";
        String repo = "spring-framework";
        Integer sinceDays = 3;
        Integer maxPages = 2;
        List<Issue> issues = issueService.getIssues(owner, repo, sinceDays, maxPages);
        assertNotNull(issues);
        System.out.println(issues);
    }
}