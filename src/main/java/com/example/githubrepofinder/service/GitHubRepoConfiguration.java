package com.example.githubrepofinder.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
class GitHubRepoConfiguration {
    @Bean
    GithubRepoFinderService githubRepoService(final WebClient.Builder webClientBuilder) {
        return new GitHubRepoFinderServiceImpl(webClientBuilder);
    }
}
