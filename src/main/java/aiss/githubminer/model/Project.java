package aiss.githubminer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Project {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("html_url")
    private String web_url;
    private List<Commit> commits;
    private List<Issue> issues;

    public String id() { return id; }

    public Project setId(String id) { this.id = id; return this; }

    public String name() { return name; }

    public Project setName(String name) { this.name = name; return this; }

    public String web_url() { return web_url; }

    public Project setWeb_url(String web_url) { this.web_url = web_url; return this; }

    public List<Commit> commits() { return commits; }

    public Project setCommits(List<Commit> commits) { this.commits = commits; return this; }

    public List<Issue> issues() { return issues; }

    public Project setIssues(List<Issue> issues) { this.issues = issues; return this; }

    public Project() {
        commits = new ArrayList<>();
        issues = new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Project.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("commits");
        sb.append('=');
        sb.append(((this.commits == null)?"<null>":this.commits));
        sb.append(',');
        sb.append("issues");
        sb.append('=');
        sb.append(((this.issues == null)?"<null>":this.issues));
        sb.append(',');

        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}