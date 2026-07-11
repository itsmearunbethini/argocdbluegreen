package com.githubdashboard.config;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.net.URI;

/** Configuration used for authenticated GitHub REST API requests. */
@Validated
@ConfigurationProperties(prefix = "github")
public record GitHubProperties(
        @NotBlank String organization,
        @NotBlank String token,
        URI apiBaseUrl
) {
    public GitHubProperties {
        if (apiBaseUrl == null) {
            apiBaseUrl = URI.create("https://api.github.com");
        }
    }
}
