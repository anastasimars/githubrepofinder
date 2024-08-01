package com.example.githubrepofinder.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.*;

@AllArgsConstructor
@Getter
public class RepoFinderResponse {
    @JsonProperty("name")
    private String repositoryName;

    private String ownerLogin;
    @JsonProperty("fork")
    private boolean fork;
    private String branchesUrl;
    private List<BranchInfoResponse> branches;

    @JsonProperty("branches_url")
    public void setBranchesUrl(String branchesUrl) {
        this.branchesUrl = branchesUrl.replace("{/branch}", "");
    }

    @JsonProperty("owner")
    public void setOwnerLogin(Map<String, Object> owner) {
        this.ownerLogin = (String) owner.get("login");
    }
}
