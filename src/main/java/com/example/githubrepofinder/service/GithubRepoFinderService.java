package com.example.githubrepofinder.service;

import com.example.githubrepofinder.response.GithubRepoFinderResponse;

import java.util.List;

public interface GithubRepoFinderService {
    List<GithubRepoFinderResponse> getAllRepos(String username);
}
