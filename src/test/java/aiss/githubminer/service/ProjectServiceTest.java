package aiss.githubminer.service;

import aiss.githubminer.model.Project;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProjectServiceTest {

    @Autowired
    ProjectService projectService;

    @Test
    @DisplayName("Get a project")
    void getProject() {
        String owner = "spring-projects";
        String repo = "spring-framework";
        Integer sinceCommits = 4;
        Integer sinceIssues= 3;
        Integer maxPages = 2;
        Project project = projectService.getProject(owner, repo, sinceCommits, sinceIssues, maxPages);
        assertNotNull(project);
        assertEquals("spring-projects", project.getName());
        assertNotNull(project.getCommits());
        assertNotNull(project.getIssues());
    }

    @Test
    void createProject() {
        String owner = "spring-projects";
        String repo = "spring-framework";
        Integer sinceCommits = 4;
        Integer sinceIssues= 3;
        Integer maxPages = 2;
        Project project = projectService.getProject(owner, repo, sinceCommits, sinceIssues, maxPages);
        assertNotNull(project);
        assertEquals("spring-projects", project.getName());
        assertNotNull(project.getCommits());
        assertNotNull(project.getIssues());
    }
}