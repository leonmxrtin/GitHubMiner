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
            issue.setComments(commentService.getIssueComments(owner, repo, sinceDays, maxPages));
        }

        return issues;
    }
}
