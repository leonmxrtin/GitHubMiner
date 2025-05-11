package aiss.githubminer.service;

import aiss.githubminer.model.Project;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        Integer sinceCommits = 30;
        Integer sinceIssues = 30;
        Integer maxPages = 2;

        String expectedProjectName = "spring-framework";
        String expectedProjectId = "1148753";
        String expectedProjectWebUrl = "https://github.com/spring-projects/spring-framework";
        String expectedProjectCommitId = "33aeb6ee9c6ba2df235ebb09cf9490c92212eee7";
        String expectedProjectIssueId = "34875";

        Project project = projectService.getProject(owner, repo, sinceCommits, sinceIssues, maxPages);

        assertNotNull(project);
        assertNotNull(project.getCommits());
        assertNotNull(project.getIssues());

        assertEquals(expectedProjectName, project.getName());
        assertEquals(expectedProjectId, project.getId());
        assertEquals(expectedProjectWebUrl, project.getWebUrl());

        assertTrue(project.getCommits().stream().anyMatch(c -> c.getId().equals(expectedProjectCommitId)));
        assertTrue(project.getIssues().stream().anyMatch(i -> i.getId().equals(expectedProjectIssueId)));

        System.out.println(project);
    }


    @Test
    @DisplayName("Create a project in GitMiner")
    void createProject() {
        String owner = "spring-projects";
        String repo = "spring-framework";
        Integer sinceCommits = 30;
        Integer sinceIssues = 30;
        Integer maxPages = 2;

        String expectedProjectName = "spring-framework";
        String expectedProjectId = "1148753";
        String expectedProjectWebUrl = "https://github.com/spring-projects/spring-framework";
        String expectedProjectCommitId = "33aeb6ee9c6ba2df235ebb09cf9490c92212eee7";
        String expectedProjectIssueId = "34875";

        String project = projectService.createProject(owner, repo, sinceCommits, sinceIssues, maxPages);

        assertNotNull(project);

        assertTrue(project.contains(expectedProjectName));
        assertTrue(project.contains(expectedProjectId));
        assertTrue(project.contains(expectedProjectWebUrl));
        assertTrue(project.contains(expectedProjectCommitId));
        assertTrue(project.contains(expectedProjectIssueId));

        System.out.println(project);
    }
}