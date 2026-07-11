package com.githubdashboard.client;

import com.githubdashboard.client.dto.GitHubRepositoryResponse;
import com.githubdashboard.exception.GitHubApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

import java.util.List;

@Component
public class GitHubRestClient implements GitHubClient {

    private static final Logger log = LoggerFactory.getLogger(GitHubRestClient.class);
    private static final ParameterizedTypeReference<List<GitHubRepositoryResponse>> REPOSITORIES_TYPE = new ParameterizedTypeReference<>() { };

    private final RestClient restClient;

    public GitHubRestClient(RestClient gitHubRestClient) {
        this.restClient = gitHubRestClient;
    }

    @Override
    public List<GitHubRepositoryResponse> getOrganizationRepositories(String organization) {
        try {
            List<GitHubRepositoryResponse> repositories = restClient.get()
                    .uri("/orgs/{organization}/repos?per_page=100&sort=updated", organization)
                    .retrieve()
                    .body(REPOSITORIES_TYPE);
            return repositories == null ? List.of() : repositories;
        } catch (RestClientResponseException exception) {
            log.warn("GitHub API request for organization '{}' failed with status {}", organization, exception.getStatusCode());
            throw new GitHubApiException("GitHub API request failed", exception);
        }
    }
}
