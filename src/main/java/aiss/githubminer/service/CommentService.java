package aiss.githubminer.service;

import aiss.githubminer.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${github.uri}")
    private String githubUri;

    public List<Comment> getIssueCommentsSince(String owner, String repo, Integer sinceDays, Integer maxPages) {
        String commentsUri = githubUri + "/repos/" + owner + "/" + repo + "/issues/comments";
        String isoDate = LocalDateTime.now().minusDays(sinceDays).toLocalDate().toString();

        List<Comment> comments = new ArrayList<>();

        // Fetch data from the API until limits are satisfied
        for (int page = 1; page <= maxPages; page++) {
            String pageUri = commentsUri + "?since=" + isoDate + "&page=" + page;
            Comment[] fetchedComments = restTemplate.getForObject(pageUri, Comment[].class);

            if (fetchedComments != null) {
                comments.addAll(Arrays.stream(fetchedComments).toList());
            }
        }

        return comments;
    }
}
