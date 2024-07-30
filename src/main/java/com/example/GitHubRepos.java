package com.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.concurrent.CountDownLatch;

public class GitHubRepos {
    public static void main(String[] args) {
        WebClient webClient = WebClient.create("https://api.github.com");

        CountDownLatch latch = new CountDownLatch(1);

        Flux<GitHubRepo> repos = webClient.get()
                .uri("/users/kypamu4/repos")
                .header(HttpHeaders.ACCEPT, "application/vnd.github+json")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(GitHubRepo.class)
                .doOnTerminate(latch::countDown)
                .doOnError(error -> System.err.println("Request error: " + error.getMessage()))
                .onErrorResume(error -> {
                    // Додаткові дії при помилці
                    System.err.println("Resuming after error...");
                    return Flux.empty();
                });



        repos.subscribe(
                repo -> System.out.println("Repository Name: " + repo.getFull_name()),
                error -> System.err.println("Subscription error: " + error.getMessage()),
                () -> System.out.println("Done!")
        );

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    static class GitHubRepo {
        @JsonProperty("full_name")
        private String full_name;

    }
}

