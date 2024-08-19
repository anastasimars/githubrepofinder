package com.example.githubrepofinder;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
class GitHubRepoFinderApiImplTest {


    private WireMockServer wireMockServer;

    @BeforeEach
    void setUp() {
        startWireMockServer();
        RestAssured.baseURI = "http://localhost:8080";
    }

    @AfterEach
    void tearDown() {
        stopWireMockServer();
    }

    @Test
    void happyPath_fetchAllRepos_shouldReturn200StatusCode() throws IOException {
        // Given
        final String givenUsername = "anastasimars";


        // When
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .when()
                .get("/api/github/users/" + givenUsername + "/repos")
                .andReturn();

        // Then
        // Line for debuging
        // FileUtils.writeStringToFile(new File("target/response.json"), response.getBody().asString(), "UTF-8");
        Assertions.assertEquals(200, response.statusCode());
    }

    private void startWireMockServer() {
        WireMockConfiguration options = new WireMockConfiguration().port(8888);
        wireMockServer = new WireMockServer(options);


        wireMockServer.stubFor(
                WireMock.get(WireMock.urlPathEqualTo("/users/anastasimars/repos"))
                        .willReturn(
                                WireMock.aResponse()
                                        .withStatus(200)
                                        .withHeader("Content-Type", "application/json; charset=utf-8")
                                        .withBody(resource("/API-TEST/mocks/mainResponse.json"))
                        )
        );

        wireMockServer.stubFor(
                WireMock.get(WireMock.urlPathMatching("/repos/anastasimars/.*"))
                        .willReturn(
                                WireMock.aResponse()
                                        .withStatus(200)
                                        .withHeader("Content-Type", "application/json; charset=utf-8")
                                        .withBody(resource("/API-TEST/mocks/branchResponse.json"))
                        )
        );

        wireMockServer.start();
    }

    private void stopWireMockServer() {
        if (wireMockServer != null && wireMockServer.isRunning()) {
            wireMockServer.stop();
        }
    }

    private static String resource(String path) {
        try {
            URL resourceURL = GitHubRepoFinderApiImplTest.class.getResource(path);
            File file = new File(Objects.requireNonNull(resourceURL).toURI());
            BufferedReader reader = new BufferedReader(new java.io.FileReader(file));
            return reader.lines().reduce("", (acc, line) -> acc + line);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
