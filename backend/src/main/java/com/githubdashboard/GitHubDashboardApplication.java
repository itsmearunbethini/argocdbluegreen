package com.githubdashboard;

import com.githubdashboard.config.GitHubProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(GitHubProperties.class)
public class GitHubDashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(GitHubDashboardApplication.class, args);
    }
}
