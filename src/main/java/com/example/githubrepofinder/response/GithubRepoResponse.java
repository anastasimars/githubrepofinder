package com.example.githubrepofinder.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GithubRepoResponse {
    private String name;
    private boolean fork;
    private String owner;
    private String branches_url;
}
