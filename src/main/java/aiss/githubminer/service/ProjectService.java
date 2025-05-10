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

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Service
public class ProjectService {

    @Value("${githubAPI.token}")
    private String token;

    @Value(("${github.uri}"))
    private String baseUri;

    @Autowired
    RestTemplate restTemplate;

    final String gitMinerUri = "http://localhost:8080/gitminer/projects";

    // TODO: add commit and issue logic
    public Project getProject(String owner, String repo, Integer sinceCommits, Integer sinceIssues, Integer maxPages)
            throws ProjectNotFoundException {
        String uri = baseUri + "/repos/" + owner + "/" + repo + "?perPage=" + maxPages;
        ResponseEntity<Project> response;
//        List<Commit> commits = new ArrayList<>();
//        List<Issue> issues = new ArrayList<>();
        if (token != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);
            HttpEntity<Object> entity = new HttpEntity<>(null, headers);
            response = restTemplate.exchange(uri, HttpMethod.GET, entity, Project.class);
        } else {
            response = restTemplate.getForEntity(uri, Project.class);
            if (response.getStatusCode() != HttpStatus.OK) {
                throw new ProjectNotFoundException();
            }
        }
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new ProjectNotFoundException();
        }
        Project project = response.getBody();
//        commits = commitService.getCommits(owner, repo, sinceCommits, maxPages);
//        issues = issueService.getIssues(owner, repo, sinceIssues, maxPages);
//        if (!isNull(commits)) {
//            project.setCommits(commits);
//        }
//        project.setCommits(commits);
//        if (!isNull(issues)) {
//            project.setIssues(issues);
//        }
        return project;
    }

    public Project createProject(String owner, String repo, Integer sinceCommits, Integer sinceIssues, Integer maxPages)
            throws ProjectNotFoundException {
        Project project = getProject(owner, repo, sinceCommits, sinceIssues, maxPages);
        return restTemplate.postForObject(gitMinerUri, project, Project.class);
    }
}
