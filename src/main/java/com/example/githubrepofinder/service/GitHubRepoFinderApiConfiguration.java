package com.example.githubrepofinder.service;

import com.example.githubrepofinder.webclient.GitHubWebClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class GitHubRepoFinderApiConfiguration {
    @Bean
    GithubRepoFinderApi githubRepoService(final GitHubWebClient gitHubWebClient) {
        return new GitHubRepoFinderApiImpl(gitHubWebClient);
    }
}
