package com.example.githubrepofinder.controller;

import com.example.githubrepofinder.response.RepoFinderResponse;
import com.example.githubrepofinder.service.GithubRepoFinderService;
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

    private final GithubRepoFinderService service;

    @GetMapping("/users/{username}/repos")
    public Mono<ResponseEntity<List<RepoFinderResponse>>> getReposByUsername
            (@PathVariable String username) {
        return null;

    }
}
