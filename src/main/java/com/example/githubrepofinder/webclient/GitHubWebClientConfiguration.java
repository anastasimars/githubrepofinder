package com.example.githubrepofinder.webclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
class GitHubWebClientConfiguration {
    @Bean
    GitHubWebClient gitHubWebClient(final WebClient.Builder webClient,
                                    final @Value("${git.api.url}") String apiUrl) {
        return new GitHubWebClient(webClient.baseUrl(apiUrl).build());
    }
}
