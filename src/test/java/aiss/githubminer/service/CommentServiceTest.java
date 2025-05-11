package aiss.githubminer.service;

import aiss.githubminer.model.Comment;
import aiss.githubminer.model.Issue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CommentServiceTest {
    @Autowired
    CommentService commentService;
    @Test
    void getIssueComments() {
        String owner = "spring-projects";
        String repo = "spring-framework";
        String issueId = "34878";
        Integer sinceDays = 3;
        Integer maxPages = 2;
        List<Comment> comments = commentService.getIssueComments(owner, repo, issueId, sinceDays, maxPages);
        assertNotNull(comments);
        System.out.println(comments);
    }
}