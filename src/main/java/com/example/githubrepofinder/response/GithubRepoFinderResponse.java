package com.example.githubrepofinder.response;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class GithubRepoFinderResponse {
    private String repositoryName;
    private String ownerLogin;
    private List<BranchInfoResponse> branches;
}
