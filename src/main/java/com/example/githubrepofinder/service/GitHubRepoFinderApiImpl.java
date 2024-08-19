package com.example.githubrepofinder.service;

import com.example.githubrepofinder.model.RepositoryData;
import com.example.githubrepofinder.webclient.GitHubWebClient;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.List;

@AllArgsConstructor
class GitHubRepoFinderApiImpl implements GithubRepoFinderApi {

    private final GitHubWebClient webClient;

    @Override
    public Mono<List<RepositoryData>> fetchAllRepos(String username) {
        return webClient.getAllUserRepos(username);
    }

}
