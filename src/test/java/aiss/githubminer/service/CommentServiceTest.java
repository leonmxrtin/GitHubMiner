package aiss.githubminer.service;

import aiss.githubminer.model.Comment;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("Get comments of an issue")
    void getIssueComments() {
        String owner = "spring-projects";
        String repo = "spring-framework";
        String issueId = "22968";
        Integer sinceDays = 3650;
        Integer maxPages = 2;

        String expectedCommentId = "492051943";
        String expectedCommentBody = "Spring Framework can work in [GraalVM](https://www.graalvm.org/) mode will be excited. And Which spring framework release version do we plan to support GraalVM with native image? 5.X Or ? Thanks ";

        List<Comment> comments = commentService.getIssueComments(owner, repo, issueId, sinceDays, maxPages);
        assertNotNull(comments);
        assertFalse(comments.isEmpty());
        assertTrue(comments.stream().anyMatch(comment -> comment.getId().equals(expectedCommentId) && comment.getBody().equals(expectedCommentBody)));
        System.out.println(comments);
    }
}