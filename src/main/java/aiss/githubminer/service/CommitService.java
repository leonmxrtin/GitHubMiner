package aiss.githubminer.service;

import aiss.githubminer.model.Commit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CommitService {

    @Autowired
    RestTemplate restGitHub;

    public List<Commit> getCommits(String owner, String repo, Integer sinceDays, Integer maxPages) {
        String commitsUri = "/repos/" + owner + "/" + repo + "/commits";
        String isoDate = LocalDateTime.now().minusDays(sinceDays).toLocalDate().toString();

        List<Commit> commits = new ArrayList<>();

        // Fetch data from the API until limits are satisfied
        for (int page = 1; page <= maxPages; page++) {
            String pageUri = commitsUri + "?since=" + isoDate + "&page=" + page;
            Commit[] fetchedCommits = restGitHub.getForObject(pageUri, Commit[].class);

            if (fetchedCommits != null) {
                commits.addAll(Arrays.stream(fetchedCommits).toList());
            }
        }

        return commits;
    }
}
