package com.example.githubrepofinder;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;

import static com.example.githubrepofinder.PathExtractor.resource;

public class StubData {


    public static StubMapping createUserReposStub(WireMockServer wireMockServer, String username, String jsonResponsePath) {
        return wireMockServer.stubFor(WireMock.get(WireMock.urlPathEqualTo("/users/" + username + "/repos"))
                .willReturn(
                        WireMock.aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json; charset=utf-8")
                                .withBody(resource(jsonResponsePath))
                ));
    }

    public static StubMapping createRepoDetailsStub(WireMockServer wireMockServer, String username, String jsonResponsePath) {
        return wireMockServer.stubFor(WireMock.get(WireMock.urlPathMatching("/repos/" + username + "/.*"))
                .willReturn(
                        WireMock.aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json; charset=utf-8")
                                .withBody(resource(jsonResponsePath))
                ));
    }

    public static StubMapping createNotFoundStub(WireMockServer wireMockServer, String username, String jsonResponsePath) {
        return wireMockServer.stubFor(WireMock.get(WireMock.urlPathMatching("/users/" + username + "/repos"))
                .willReturn(WireMock
                        .aResponse()
                        .withStatus(404)
                        .withHeader("Content-Type", "application/json; charset=utf-8")
                        .withBody(resource(jsonResponsePath))));
    }

}

