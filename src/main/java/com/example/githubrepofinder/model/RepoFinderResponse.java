package com.example.githubrepofinder.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class RepoFinderResponse {
    private String name;

    private Owner owner;
    private boolean fork;
    @JsonProperty("branches_url")
    private String branchesUrl;
    private List<BranchInfoResponse> branches;
}