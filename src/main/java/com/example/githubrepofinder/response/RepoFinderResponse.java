package com.example.githubrepofinder.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class RepoFinderResponse {
    private String repositoryName;
    private String ownerLogin;
    private boolean fork;
    private List<BranchInfoResponse> branches;
}
