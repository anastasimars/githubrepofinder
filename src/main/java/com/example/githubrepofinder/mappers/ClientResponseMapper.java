package com.example.githubrepofinder.mappers;

import com.example.githubrepofinder.model.BranchInfoResponse;
import com.example.githubrepofinder.model.RepositoryData;
import com.example.githubrepofinder.model.RepoFinderResponse;

import java.util.List;

public class ClientResponseMapper {

    public static RepositoryData mapToClientResponse(RepoFinderResponse repoFinderResponse,
                                                     List<BranchInfoResponse> branchInfoResponses){
        return new RepositoryData(repoFinderResponse.getRepositoryName(),
                repoFinderResponse.getOwnerLogin(), branchInfoResponses);
    }
}
