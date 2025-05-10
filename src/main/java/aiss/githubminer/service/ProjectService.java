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

    // @Value("${github.token}")
    private String token;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CommitService commitService;

    @Value("${github.uri}")
    private String githubUri;

    @Value("${gitminer.uri}")
    private String gitminerUri;

    // TODO: add commit and issue logic
    public Project getProject(String owner, String repo, Integer sinceCommits, Integer sinceIssues, Integer maxPages)
            throws ProjectNotFoundException {
        String repoUri = githubUri + "/repos/" + owner + "/" + repo;

        ResponseEntity<Project> response = restTemplate.getForEntity(repoUri, Project.class);

//        if (token != null) {
//            HttpHeaders headers = new HttpHeaders();
//            headers.set("Authorization", "Bearer " + token);
//            HttpEntity<Object> entity = new HttpEntity<>(null, headers);
//            response = restTemplate.exchange(uri, HttpMethod.GET, entity, Project.class);
//            if (response.getStatusCode() != HttpStatus.OK) {
//                throw new ProjectNotFoundException();
//            }
////            commits = restTemplate.exchange(uri + "/commits", HttpMethod.GET, entity, Commit[].class);
////            issues = restTemplate.getForObject(uri + "/issued", HttpMethod.GET, entity, Issue[].class);
//        } else {
//            response = restTemplate.getForEntity(uri, Project.class);
//            if (response.getStatusCode() != HttpStatus.OK) {
//                throw new ProjectNotFoundException();
//            }
////            Commit[] commits = restTemplate.getForObject(uri + "/commits", Commit[].class);
////            Issue[] issues = restTemplate.getForObject(uri + "/issues", Issue[].class);
//        }

        if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null) {
            throw new ProjectNotFoundException();
        }

        Project project = response.getBody();
        project.setCommits(commitService.getCommits(owner, repo, sinceIssues, maxPages));

        return project;
    }

    public Project createProject(String owner, String repo, Integer sinceCommits, Integer sinceIssues, Integer maxPages)
            throws ProjectNotFoundException {
        Project project = getProject(owner, repo, sinceCommits, sinceIssues, maxPages);
        return restTemplate.postForObject(gitminerUri + "/projects", project, Project.class);
    }
}
