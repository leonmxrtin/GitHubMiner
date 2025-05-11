package aiss.githubminer.controller;

import aiss.githubminer.exception.ProjectNotFoundException;
import aiss.githubminer.model.Project;
import aiss.githubminer.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/github")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @Operation(summary = "Retrieve a project",
            description = "Get a project given its owner and name",
            tags = {"projects", "get"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = {@Content(schema = @Schema(implementation = Project.class), mediaType = "application/json")})
    })
    @GetMapping("/{owner}/{repoName}")
    public Project getProject(@Parameter(description = "Owner of the project")
                                  @PathVariable String owner,
                              @Parameter(description = "Name of the project")
                                  @PathVariable String repoName,
                              @RequestParam(defaultValue = "2") Integer sinceCommits,
                              @RequestParam(defaultValue = "20") Integer sinceIssues,
                              @RequestParam(defaultValue = "2") Integer maxPages)
            throws ProjectNotFoundException {
        return projectService.getProject(owner, repoName, sinceCommits, sinceIssues, maxPages);
    }

    @Operation(summary = "Retrieve and store a project",
            description = "Get a project given its owner and name and send it to GitMiner",
            tags = {"projects", "post"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = {@Content(schema = @Schema(implementation = String.class), mediaType = "application/json")})
    })
    @PostMapping("/{owner}/{repoName}")
    public String createProject(@PathVariable String owner,
                              @PathVariable String repoName,
                              @RequestParam(defaultValue = "2") Integer sinceCommits,
                              @RequestParam(defaultValue = "20") Integer sinceIssues,
                              @RequestParam(defaultValue = "2") Integer maxPages)
            throws ProjectNotFoundException {
        return projectService.createProject(owner, repoName, sinceCommits, sinceIssues, maxPages);
    }
}
