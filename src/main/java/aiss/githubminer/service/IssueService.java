package aiss.githubminer.service;

import aiss.githubminer.model.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class IssueService {

    @Autowired
    RestTemplate restGitHub;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    public List<Issue> getIssues(String owner, String repo, Integer sinceDays, Integer maxPages) {
        String issuesUri = "/repos/" + owner + "/" + repo + "/issues";
        String isoDate = LocalDateTime.now().minusDays(sinceDays).toLocalDate().toString();

        List<Issue> issues = new ArrayList<>();

        // Fetch data from the API until limits are satisfied
        for (int page = 1; page <= maxPages; page++) {
            String pageUri = issuesUri + "?since=" + isoDate + "&page=" + page;
            Issue[] fetchedIssues = restGitHub.getForObject(pageUri, Issue[].class);

            if (fetchedIssues != null) {
                issues.addAll(Arrays.stream(fetchedIssues).toList());
            }
        }

        for (Issue issue : issues) {
            // Update the User object because response from issues endpoint does not include the user's full name.
            // This process slows down the fetching process.
            if (issue.getAssignee() != null) {
                String assigneeUsername = issue.getAssignee().getUsername();
                String assigneeName = userService.getUser(assigneeUsername).getName();
                issue.getAssignee().setName(assigneeName);
            }
            String authorUsername = issue.getAuthor().getUsername();
            String authorName = userService.getUser(authorUsername).getName();
            issue.getAuthor().setName(authorName);

            issue.setComments(commentService.getIssueComments(owner, repo, sinceDays, maxPages));
        }

        return issues;
    }
}
