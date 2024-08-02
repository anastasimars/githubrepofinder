package com.example.githubrepofinder.model;


import java.util.List;


public record UserRepositoryResponse(List<RepositoryData> repositoriesData) {
}
