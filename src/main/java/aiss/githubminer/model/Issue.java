package aiss.githubminer.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;

public class Issue {

    public Issue() {
        labels = new ArrayList<>();
    }

    private String title;
    private String state;
    private User assignee;

    // Ignore comments value from Issue JSON, as it corresponds to the comments amount, not a list.
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Comment> comments;

    @JsonAlias("number")
    private String id;

    @JsonAlias("user")
    private User author;

    @JsonAlias("body")
    private String description;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    @JsonProperty("closed_at")
    private String closedAt;

    private Integer votes;
    @JsonProperty("reactions")
    private void unpackReactions(JsonNode reactions) {
        this.votes = reactions.get("total_count").asInt();
    }

    private List<String> labels;
    @JsonProperty("labels")
    private void unpackLabels(JsonNode labels) {
        for (JsonNode label : labels) {
            this.labels.add(label.get("name").asText());
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(String closedAt) {
        this.closedAt = closedAt;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "title='" + title + '\'' +
                ", state='" + state + '\'' +
                ", assignee=" + assignee +
                ", comments=" + comments +
                ", id='" + id + '\'' +
                ", author=" + author +
                ", description='" + description + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", closedAt='" + closedAt + '\'' +
                ", votes=" + votes +
                ", labels=" + labels +
                '}';
    }
}
