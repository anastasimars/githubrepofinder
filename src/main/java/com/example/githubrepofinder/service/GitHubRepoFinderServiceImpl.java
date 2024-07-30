package com.example.githubrepofinder.service;

import com.example.githubrepofinder.response.BranchInfoResponse;
import com.example.githubrepofinder.response.GithubRepoResponse;
import com.example.githubrepofinder.response.RepoFinderResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@AllArgsConstructor
class GitHubRepoFinderServiceImpl implements GithubRepoFinderService {

    private final WebClient.Builder webClientBuilder;
    private static final String BASE_GITHUB_URL = "https://api.github.com";

    @Override
    public Mono<List<RepoFinderResponse>> getAllRepos(String username) {
        return webClientBuilder.build()
                .get()
                .uri(BASE_GITHUB_URL + "/users/{username}/repos", username)
                .header(HttpHeaders.ACCEPT, "application/vnd.github+json")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(GithubRepoResponse.class)
                .filter(githubRepoResponse -> !githubRepoResponse.isFork())
                .flatMap(githubRepoResponse -> getAllBranches(githubRepoResponse)
                        .map(branchInfoResponses -> (RepoFinderResponse) null))
                .collectList();
    }

    private Flux<BranchInfoResponse> getAllBranches(
            GithubRepoResponse githubRepoResponse) {
        return webClientBuilder.build()
                .get()
                .uri(githubRepoResponse.getBranches_url().replace("branch/", ""))
                .header(HttpHeaders.ACCEPT, "application/vnd.github+json")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(BranchInfoResponse.class);
    }


}
