package com.example.githubrepofinder.controller;

import com.example.githubrepofinder.model.ClientResponse;
import com.example.githubrepofinder.service.GithubRepoFinderApi;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/github")
@AllArgsConstructor
class GitHubRepoFinderController {

    private final GithubRepoFinderApi service;

    @GetMapping(value = "/users/{username}/repos", produces = "application/json")
    public Mono<ResponseEntity<List<ClientResponse>>> getReposByUsername
            (@PathVariable String username) {
        return service.getAllRepos(username)
                .map(ResponseEntity::ok);
    }
}
