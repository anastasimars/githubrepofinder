package com.example.githubrepofinder.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
@Getter
public class BranchInfoResponse {
    @JsonProperty("name")
    private String branchName;
    private String lastCommitSha;
    @JsonProperty("commit")
    public void setLastCommitSha(Map<String, Object> commit) {
        this.lastCommitSha = (String) commit.get("sha");
    }
}
