package com.example.githubrepofinder.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class GitHubRepoConfiguration {
    @Bean
    GithubRepoFinderService githubRepoService() {
        return new GitHubRepoFinderServiceImpl();
    }
}
