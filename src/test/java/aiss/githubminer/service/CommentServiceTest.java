package aiss.githubminer.service;

import aiss.githubminer.model.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.Optional;

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
        String expectedCommentCreatedAt = "2019-05-14T02:14:23Z";
        String expectedCommentUpdatedAt = "2019-05-14T02:14:23Z";
        String expectedCommentAuthorId = "32863418";

        List<Comment> comments = commentService.getIssueComments(owner, repo, issueId, sinceDays, maxPages);
        assertNotNull(comments);
        assertFalse(comments.isEmpty());

        Optional<Comment> optComment = comments.stream().filter(c -> c.getId().equals(expectedCommentId)).findFirst();
        assertTrue(optComment.isPresent());

        Comment comment = optComment.get();
        assertEquals(expectedCommentId, comment.getId());
        assertEquals(expectedCommentBody, comment.getBody());
        assertEquals(expectedCommentCreatedAt, comment.getCreatedAt());
        assertEquals(expectedCommentUpdatedAt, comment.getUpdatedAt());
        assertEquals(expectedCommentAuthorId, comment.getAuthor().getId()); // no need to test all User fields, as we have specific tests for User too

        System.out.println(comments);
    }
}