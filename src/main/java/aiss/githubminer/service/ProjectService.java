package aiss.githubminer.service;

import aiss.githubminer.exception.ProjectNotFoundException;
import aiss.githubminer.model.Commit;
import aiss.githubminer.model.Issue;
import aiss.githubminer.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProjectService {

    @Value("${githubAPI.token}")
    private String token;

    @Autowired
    RestTemplate restTemplate;

    private final String baseUri = "https://api.github.com/repos";

    private final String gitMinerUri = "http://localhost:8080/gitminer/projects";

    // TODO: add commit and issue logic
    public Project getProject(String owner, String repo, Integer sinceCommits, Integer sinceIssues, Integer maxPages)
        throws ProjectNotFoundException {
        String uri = baseUri + "/" + owner + "/" + repo + "?perPage=" + maxPages;
        ResponseEntity<Project> response;
        ResponseEntity<Commit[]> commits = null;
        ResponseEntity<Issue[]> issues = null;
        if (token != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);
            HttpEntity<Object> entity = new HttpEntity<>(null, headers);
            response = restTemplate.exchange(uri, HttpMethod.GET, entity, Project.class);
            if (response.getStatusCode() != HttpStatus.OK) {
                throw new ProjectNotFoundException();
            }
//            commits = restTemplate.exchange(uri + "/commits", HttpMethod.GET, entity, Commit[].class);
//            issues = restTemplate.getForObject(uri + "/issued", HttpMethod.GET, entity, Issue[].class);
        } else {
            response = restTemplate.getForEntity(uri, Project.class);
            if (response.getStatusCode() != HttpStatus.OK) {
                throw new ProjectNotFoundException();
            }
//            Commit[] commits = restTemplate.getForObject(uri + "/commits", Commit[].class);
//            Issue[] issues = restTemplate.getForObject(uri + "/issues", Issue[].class);
        }
        Project project = response.getBody();
//        if (!isNull(commits)) {
//            commits = Arrays.stream(commits).toList().limit(nCommits);
//            project.setCommits(commits);
//        }
//        if (!isNull(issues)) {
//            issues = Arrays.stream(issues).toList().limit(nIssues);
//            project.setIssues(issues);
//        }
        return project;
    }

    public Project createProject(String owner, String repo, Integer sinceCommits, Integer sinceIssues, Integer maxPages) {
        Project project = getProject(owner, repo, sinceCommits, sinceIssues, maxPages);
        return restTemplate.postForObject(gitMinerUri, project, Project.class);
    }
}
