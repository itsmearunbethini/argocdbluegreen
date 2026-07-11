package com.githubdashboard.client;

import com.githubdashboard.client.dto.GitHubRepositoryResponse;

import java.util.List;

/** Boundary for all outbound calls to the GitHub REST API. */
public interface GitHubClient {

    List<GitHubRepositoryResponse> getOrganizationRepositories(String organization);
}
