package com.example.githubrepofinder.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
@AllArgsConstructor
@Getter
public class RepositoryData {
    private String name;
    private String owner;
    private List<BranchInfoResponse> branches;
}
