package com.example.githubrepofinder.service;

import com.example.githubrepofinder.model.RepositoryData;
import reactor.core.publisher.Mono;

import java.util.List;

public interface GithubRepoFinderApi {
   Mono<List<RepositoryData>> fetchAllRepos(String username);
}
