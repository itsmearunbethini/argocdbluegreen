package com.githubdashboard.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Minimal GitHub repository representation required by dashboard metrics. */
public record GitHubRepositoryResponse(
        String name,
        @JsonProperty("stargazers_count") long stargazersCount,
        @JsonProperty("forks_count") long forksCount,
        @JsonProperty("open_issues_count") long openIssuesCount
) {
}
