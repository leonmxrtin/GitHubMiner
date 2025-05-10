package aiss.githubminer.service;

import aiss.githubminer.exception.ProjectNotFoundException;
import aiss.githubminer.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProjectService {

    @Autowired
    RestTemplate restGitHub;

    @Autowired
    @Qualifier("GitMiner")
    RestTemplate restGitMiner;

    @Autowired
    CommitService commitService;

    @Autowired
    IssueService issueService;

    public Project getProject(String owner, String repo, Integer sinceCommits, Integer sinceIssues, Integer maxPages)
            throws ProjectNotFoundException {
        String repoUri = "/repos/" + owner + "/" + repo;

        ResponseEntity<Project> response = restGitHub.getForEntity(repoUri, Project.class);

        if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null) {
            throw new ProjectNotFoundException();
        }

        Project project = response.getBody();
        project.setCommits(commitService.getCommits(owner, repo, sinceCommits, maxPages));
        project.setIssues(issueService.getIssues(owner, repo, sinceIssues, maxPages));

        return project;
    }

    public Project createProject(String owner, String repo, Integer sinceCommits, Integer sinceIssues, Integer maxPages)
            throws ProjectNotFoundException {
        Project project = getProject(owner, repo, sinceCommits, sinceIssues, maxPages);
        return restGitMiner.postForObject("/projects", project, Project.class);
    }
}
