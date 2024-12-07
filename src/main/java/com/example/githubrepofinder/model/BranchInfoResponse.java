package com.example.githubrepofinder.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BranchInfoResponse {
    private String name;
    private Commit commit;
}
