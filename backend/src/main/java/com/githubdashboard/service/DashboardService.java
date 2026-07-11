package com.githubdashboard.service;

import com.githubdashboard.client.GitHubClient;
import com.githubdashboard.client.dto.GitHubRepositoryResponse;
import com.githubdashboard.config.GitHubProperties;
import com.githubdashboard.dto.DashboardOverviewResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {

    private final GitHubClient gitHubClient;
    private final GitHubProperties gitHubProperties;

    public DashboardService(GitHubClient gitHubClient, GitHubProperties gitHubProperties) {
        this.gitHubClient = gitHubClient;
        this.gitHubProperties = gitHubProperties;
    }

    public DashboardOverviewResponse getOverview() {
        List<GitHubRepositoryResponse> repositories = gitHubClient.getOrganizationRepositories(gitHubProperties.organization());
        return new DashboardOverviewResponse(
                gitHubProperties.organization(),
                repositories.size(),
                repositories.stream().mapToLong(GitHubRepositoryResponse::stargazersCount).sum(),
                repositories.stream().mapToLong(GitHubRepositoryResponse::forksCount).sum(),
                repositories.stream().mapToLong(GitHubRepositoryResponse::openIssuesCount).sum());
    }
}
