package com.example.githubrepofinder.api;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

import java.io.IOException;

import static com.example.githubrepofinder.StubData.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
class GitHubRepoFinderApiImplTest {

    @LocalServerPort
    private String appPort;

    private WireMockServer wireMockServer;

    @BeforeEach
    void setUp() {
        startWireMockServer();
        RestAssured.baseURI = "http://localhost:" + appPort;
    }

    @AfterEach
    void tearDown() {
        stopWireMockServer();
    }

    @Test
    void fetchAllRepos_whenUsernameIsExist_shouldReturn200StatusCode() {
        // Given
        final String givenUsername = "anastasimars";
        final String givenMainResponse = "/API-TEST/mocks/mainResponse.json";
        final String givenBranchResponse = "/API-TEST/mocks/branchResponse.json";

        // creating stubs
        createUserReposStub(wireMockServer, givenUsername, givenMainResponse);
        createRepoDetailsStub(wireMockServer, givenUsername, givenBranchResponse);

        // When
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .get("/api/github/users/" + givenUsername + "/repos");

        // Then
        // Line for debuging
        // FileUtils.writeStringToFile(new File("target/response.json"), response.getBody().asString(), "UTF-8");
        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    void fetchAllRepos_whenUsernameIsNotExist_shouldReturn400StatusCode() throws IOException {
        // Given
        final String givenNonExistUsername = "anastasiaaamars";
        final String givenNotFoundResponse = "/API-TEST/mocks/notFoundResponse.json";

        // creating stubs
        createNotFoundStub(wireMockServer, givenNonExistUsername, givenNotFoundResponse);

        // When
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .get("/api/github/users/" + givenNonExistUsername + "/repos");

        // Then
        // Line for debuging
        // FileUtils.writeStringToFile(new File("target/response.json"), response.getBody().asString(), "UTF-8");
        Assertions.assertEquals(404, response.statusCode());
    }

    private void startWireMockServer() {
        WireMockConfiguration options = new WireMockConfiguration().port(8888);
        wireMockServer = new WireMockServer(options);
        wireMockServer.start();
    }

    private void stopWireMockServer() {
        if (wireMockServer != null && wireMockServer.isRunning()) {
            wireMockServer.stop();
        }
    }


}
