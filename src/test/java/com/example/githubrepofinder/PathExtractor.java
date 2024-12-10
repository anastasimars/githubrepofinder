package com.example.githubrepofinder;

import wiremock.org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class PathExtractor {
    public static String resource(String path) {
        try {
            URL resourceURL = PathExtractor.class.getResource(path);
            File file = new File(Objects.requireNonNull(resourceURL).toURI());
            return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
