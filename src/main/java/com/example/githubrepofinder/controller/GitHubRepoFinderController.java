package com.example.githubrepofinder.controller;

import com.example.githubrepofinder.model.RepositoryData;
import com.example.githubrepofinder.service.GithubRepoFinderApi;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/github")
@AllArgsConstructor
class GitHubRepoFinderController {

    private final GithubRepoFinderApi service;

    @GetMapping(value = "/users/{username}/repos", produces = "application/json")
    ResponseEntity<Mono<List<RepositoryData>>> getReposByUsername(@PathVariable String username) {
        return ResponseEntity.ok(service.fetchAllRepos(username));
    }
}
