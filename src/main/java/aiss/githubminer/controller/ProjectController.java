package aiss.githubminer.controller;

import aiss.githubminer.exception.ProjectNotFoundException;
import aiss.githubminer.model.Project;
import aiss.githubminer.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/github")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @GetMapping("/{owner}/{repoName}")
    public Project getProject(@PathVariable String owner,
                              @PathVariable String repoName,
                              @RequestParam(defaultValue = "2") Integer sinceCommits,
                              @RequestParam(defaultValue = "20") Integer sinceIssues,
                              @RequestParam(defaultValue = "2") Integer maxPages)
            throws ProjectNotFoundException {
        return projectService.getProject(owner, repoName, sinceCommits, sinceIssues, maxPages);
    }

    @PostMapping("/{owner}/{repoName}")
    public Project createProject(@PathVariable String owner,
                              @PathVariable String repoName,
                              @RequestParam(defaultValue = "2") Integer sinceCommits,
                              @RequestParam(defaultValue = "20") Integer sinceIssues,
                              @RequestParam(defaultValue = "2") Integer maxPages)
            throws ProjectNotFoundException {
        return projectService.createProject(owner, repoName, sinceCommits, sinceIssues, maxPages);
    }
}
