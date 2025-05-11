package aiss.githubminer.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

public class Commit {
    @JsonAlias("sha")
    private String id;

    @JsonProperty("web_url")
    @JsonAlias("html_url")
    private String webUrl;

    private String title;
    private String message;

    @JsonProperty("author_name")
    private String authorName;
    @JsonProperty("author_email")
    private String authorEmail;
    @JsonProperty("authored_date")
    private String authoredDate;

    @JsonProperty("commit")
    private void unpackCommit(JsonNode commit) {
        this.authorName = commit.get("author").get("name").asText();
        this.authorEmail = commit.get("author").get("email").asText();
        this.authoredDate = commit.get("author").get("date").asText();

        this.message = this.title = commit.get("message").asText();

        if (this.message.lines().count() > 1) {
            this.title = this.message.lines().findFirst().get();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }

    public String getAuthoredDate() {
        return authoredDate;
    }

    public void setAuthoredDate(String authoredDate) {
        this.authoredDate = authoredDate;
    }

    @Override
    public String toString() {
        return "Commit{" +
                "id='" + id + '\'' +
                ", webUrl='" + webUrl + '\'' +
                ", title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", authorName='" + authorName + '\'' +
                ", authorEmail='" + authorEmail + '\'' +
                ", authoredDate='" + authoredDate + '\'' +
                '}';
    }
}
