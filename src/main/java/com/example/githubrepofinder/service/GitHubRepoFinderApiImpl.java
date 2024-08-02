package com.example.githubrepofinder.service;

import com.example.githubrepofinder.model.RepositoryData;
import com.example.githubrepofinder.model.UserRepositoryResponse;
import com.example.githubrepofinder.webclient.GitHubWebClient;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
class GitHubRepoFinderApiImpl implements GithubRepoFinderApi {

    private final GitHubWebClient webClient;

    @Override
    public UserRepositoryResponse fetchAllRepos(String username) {
        List<RepositoryData> repositoryData = webClient.getAllUserRepos(username).block();
        return new UserRepositoryResponse(repositoryData);
    }

}
