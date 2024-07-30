package com.example.githubrepofinder.service;

import com.example.githubrepofinder.response.GithubRepoFinderResponse;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
class GitHubRepoFinderServiceImpl implements GithubRepoFinderService {
    @Override
    public List<GithubRepoFinderResponse> getAllRepos(String username) {
        return null;
    }
}
