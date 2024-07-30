package com.example.githubrepofinder.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BranchInfoResponse {
    private String branchName;
    private String lastCommitSha;
}
