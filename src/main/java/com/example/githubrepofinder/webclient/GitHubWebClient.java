package com.example.githubrepofinder.webclient;

import com.example.githubrepofinder.exception.UsernameNotFoundException;
import com.example.githubrepofinder.model.BranchInfoResponse;
import com.example.githubrepofinder.model.RepoFinderResponse;
import com.example.githubrepofinder.model.RepositoryData;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@AllArgsConstructor
public class GitHubWebClient {
    private final WebClient webClient;

    public Mono<List<RepositoryData>> getAllUserRepos(final String username) {
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
                .flatMap(repoFinderResponse -> getAllBranchesForRepo(repoFinderResponse)
                        .map(branchInfoResponses -> new RepositoryData(repoFinderResponse.getName(),
                                repoFinderResponse.getOwner().getLogin(), branchInfoResponses)))
                .collectList();
    }

    private Mono<List<BranchInfoResponse>> getAllBranchesForRepo(
            final RepoFinderResponse response) {
        final String repositoryName = response.getName();
        final String username = response.getOwner().getLogin();

        return webClient
                .get()
                .uri("/repos/{username}/{repositoryName}/branches",  username, repositoryName)
                .header(HttpHeaders.ACCEPT, "application/vnd.github+json")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(BranchInfoResponse.class)
                .collectList();
    }
}
