package com.githubdashboard.service;

import com.githubdashboard.client.GitHubClient;
import com.githubdashboard.client.dto.GitHubRepositoryResponse;
import com.githubdashboard.config.GitHubProperties;
import com.githubdashboard.dto.DashboardOverviewResponse;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DashboardServiceTest {

    @Test
    void aggregatesRepositoryMetrics() {
        GitHubClient gitHubClient = mock(GitHubClient.class);
        when(gitHubClient.getOrganizationRepositories("acme")).thenReturn(List.of(
                new GitHubRepositoryResponse("api", 4, 2, 3),
                new GitHubRepositoryResponse("web", 6, 1, 5)));
        DashboardService service = new DashboardService(
                gitHubClient,
                new GitHubProperties("acme", "token", URI.create("https://api.github.com")));

        DashboardOverviewResponse overview = service.getOverview();

        assertThat(overview.organization()).isEqualTo("acme");
        assertThat(overview.repositoryCount()).isEqualTo(2);
        assertThat(overview.totalStars()).isEqualTo(10);
        assertThat(overview.totalForks()).isEqualTo(3);
        assertThat(overview.openIssueCount()).isEqualTo(8);
    }
}
