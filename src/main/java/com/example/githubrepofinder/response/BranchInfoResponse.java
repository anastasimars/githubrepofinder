package com.example.githubrepofinder.response;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BranchInfoResponse {
    private String branchName;
    private String lastCommitSha;
}
