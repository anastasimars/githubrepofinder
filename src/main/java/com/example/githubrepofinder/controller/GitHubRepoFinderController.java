package com.example.githubrepofinder.controller;

import com.example.githubrepofinder.model.UserRepositoryResponse;
import com.example.githubrepofinder.service.GithubRepoFinderApi;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/github")
@AllArgsConstructor
class GitHubRepoFinderController {

    private final GithubRepoFinderApi service;

    @GetMapping(value = "/users/{username}/repos", produces = "application/json")
    public ResponseEntity<UserRepositoryResponse> getReposByUsername
            (@PathVariable String username) {
        return ResponseEntity.ok(service.fetchAllRepos(username));
    }
}
