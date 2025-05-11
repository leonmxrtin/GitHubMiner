package aiss.githubminer.service;

import aiss.githubminer.model.Issue;
import aiss.githubminer.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IssueServiceTest {

    @Autowired
    IssueService issueService;

    @Test
    @DisplayName("Get issues of a project")
    void getIssues() {
        String owner = "spring-projects";
        String repo = "spring-framework";
        Integer sinceDays = 30;
        Integer maxPages = 2;

        String expectedIssueId = "34875";
        String expectedIssueTitle = "Update outdated example that uses `JettyHttpHandlerAdapter` in the reference manual";
        String expectedIssueDescription = """
                > Just a quick note that https://github.com/spring-projects/spring-framework/blob/main/framework-docs/modules/ROOT/pages/web/webflux/reactive-spring.adoc still mentions the handler\s
                
                 _Originally posted by @philwebb in [#33747](https://github.com/spring-projects/spring-framework/issues/33747#issuecomment-2865256261)_""";
        String expectedIssueState = "open";
        User expectedIssueAssignee = null;
        String expectedIssueCommentId = "2868780176";
        String expectedIssueAuthorId = "104798";
        String expectedIssueCreatedAt = "2025-05-09T17:06:02Z";
        String expectedIssueUpdatedAt = "2025-05-10T12:44:12Z";
        String expectedIssueClosedAt = null;
        Integer expectedIssueVotes = 0;
        List<String> expectedIssueLabels = Collections.singletonList("type: documentation");

        List<Issue> issues = issueService.getIssues(owner, repo, sinceDays, maxPages);
        assertNotNull(issues);
        assertFalse(issues.isEmpty());

        Optional<Issue> optIssue = issues.stream().filter(i -> i.getId().equals(expectedIssueId)).findFirst();
        assertTrue(optIssue.isPresent());

        Issue issue = optIssue.get();
        assertEquals(expectedIssueId, issue.getId());
        assertEquals(expectedIssueTitle, issue.getTitle());
        assertEquals(expectedIssueDescription, issue.getDescription());
        assertEquals(expectedIssueState, issue.getState());
        assertEquals(expectedIssueAssignee, issue.getAssignee());
        assertEquals(expectedIssueAuthorId, issue.getAuthor().getId());
        assertEquals(expectedIssueCreatedAt, issue.getCreatedAt());
        assertEquals(expectedIssueUpdatedAt, issue.getUpdatedAt());
        assertEquals(expectedIssueClosedAt, issue.getClosedAt());
        assertEquals(expectedIssueVotes, issue.getVotes());
        assertEquals(expectedIssueLabels, issue.getLabels());

        assertNotNull(issue.getComments());
        assertFalse(issue.getComments().isEmpty());
        assertTrue(issue.getComments().stream().anyMatch(c -> c.getId().equals(expectedIssueCommentId)));

        System.out.println(issues);
    }
}