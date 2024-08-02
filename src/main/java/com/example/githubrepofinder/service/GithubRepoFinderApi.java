package com.example.githubrepofinder.service;

import com.example.githubrepofinder.model.UserRepositoryResponse;

public interface GithubRepoFinderApi {
   UserRepositoryResponse fetchAllRepos(String username);
}
