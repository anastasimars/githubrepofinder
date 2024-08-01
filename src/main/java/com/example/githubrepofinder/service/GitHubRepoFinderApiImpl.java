package com.example.githubrepofinder.service;

import com.example.githubrepofinder.exception.UsernameNotFoundException;
import com.example.githubrepofinder.model.BranchInfoResponse;
import com.example.githubrepofinder.model.ClientResponse;
import com.example.githubrepofinder.model.RepoFinderResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;


class GitHubRepoFinderApiImpl implements GithubRepoFinderApi {
    public GitHubRepoFinderApiImpl(WebClient.Builder webClient) {
        this.webClient = webClient.baseUrl("https://api.github.com").build();
    }

    private final WebClient webClient;

    @Override
    public Mono<List<ClientResponse>> getAllRepos(String username) {
        return webClient
                .get()
                .uri("/users/{username}/repos", username)
                .header(HttpHeaders.ACCEPT, "application/vnd.github+json")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::isError, clientResponse ->
                        {
                            if (clientResponse.statusCode().equals(HttpStatus.NOT_FOUND)) {
                                return Mono
                                        .error(new UsernameNotFoundException(username));
                            }
                            return clientResponse.createException();
                        }
                )
                .bodyToFlux(RepoFinderResponse.class)

                .filter(repoFinderResponse -> !repoFinderResponse.isFork())
                .flatMap(repoFinderResponse -> getAllBranches(repoFinderResponse)
                        .map(branchInfoResponses -> new ClientResponse(
                                repoFinderResponse.getRepositoryName(),
                                repoFinderResponse.getOwnerLogin(),
                                branchInfoResponses)
                        ))
                .collectList();
    }

    private Mono<List<BranchInfoResponse>> getAllBranches(
            RepoFinderResponse response) {
        return webClient
                .get()
                .uri(response.getBranchesUrl())
                .header(HttpHeaders.ACCEPT, "application/vnd.github+json")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(BranchInfoResponse.class)
                .collectList();
    }


}
