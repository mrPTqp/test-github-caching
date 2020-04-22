package com.github.mrptqp.githubapiwrapper.interrogators;

import com.github.mrptqp.githubapiwrapper.exceptions.BadRequestException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GithubInterrogator {

    private static final String GITHUB_API_URL = "https://api.github.com";

    private static final String GET_TOP_PROJECTS_BY_LANGUAGE =
            "/search/repositories?q=language:";

    private static final String SORT_AND_ORDER = "&sort=stars&order=desc";

    private static final String GITHUB_V3_MIME_TYPE = "application/vnd.github.v3+json";

    private final HttpHeaders headers;

    public GithubInterrogator() {
        headers = new HttpHeaders();
        headers.set("Content-Type", GITHUB_V3_MIME_TYPE);
    }

    public String getTopProjectsByLanguage(String language) {
        ResponseEntity<String> result;

        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

            result = restTemplate.exchange(
                    GITHUB_API_URL + GET_TOP_PROJECTS_BY_LANGUAGE + language + SORT_AND_ORDER,
                    HttpMethod.GET,
                    entity,
                    String.class
            );
        } catch (Exception ex) {
            throw new BadRequestException("Bad request. Please, check request structure and requested program language.");
        }

        return result.getBody();
    }
}
