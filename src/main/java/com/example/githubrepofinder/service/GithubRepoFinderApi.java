package com.example.githubrepofinder.service;

import com.example.githubrepofinder.model.ClientResponse;
import reactor.core.publisher.Mono;

import java.util.List;

public interface GithubRepoFinderApi {
    Mono<List<ClientResponse>> getAllRepos(String username);
}
