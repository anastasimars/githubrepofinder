package com.example.githubrepofinder.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ErrorMessageResponse {
    @JsonProperty("status")
    private Integer status;
    @JsonProperty("message")
    private String message;
}
