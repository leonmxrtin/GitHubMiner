package aiss.githubminer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class GitHubMinerApplication {

    @Value("${github.token}")
    private String token;

    @Value("${github.uri}")
    private String githubUri;

    @Value("${gitminer.uri}")
    private String gitminerUri;

    public static void main(String[] args) {
        SpringApplication.run(GitHubMinerApplication.class, args);
    }

    @Bean(name = "GitHub")
    @Primary
    public RestTemplate restGitHub(RestTemplateBuilder builder) {
        if (!token.isEmpty()) {
            builder = builder.defaultHeader("Authorization", "Bearer " + token);
        }
        return builder.rootUri(githubUri).build();
    }

    @Bean(name = "GitMiner")
    public RestTemplate restGitMiner(RestTemplateBuilder builder) {
        return builder.rootUri(gitminerUri).build();
    }

}
