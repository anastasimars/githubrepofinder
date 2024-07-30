package com.example.githubrepofinder.service;

import com.example.githubrepofinder.response.RepoFinderResponse;
import reactor.core.publisher.Mono;

import java.util.List;

public interface GithubRepoFinderService {
    Mono<List<RepoFinderResponse>> getAllRepos(String username);
}
