package com.githubdashboard.dto;

/** Aggregated repository metrics for the configured GitHub organization. */
public record DashboardOverviewResponse(
        String organization,
        int repositoryCount,
        long totalStars,
        long totalForks,
        long openIssueCount
) {
}
