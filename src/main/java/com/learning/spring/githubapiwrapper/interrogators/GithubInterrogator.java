package com.learning.spring.githubapiwrapper.interrogators;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class GithubInterrogator {
    private static final String GITHUB_API_URL = "https://api.github.com";
    private static final String GET_TOP_PROJECTS_BY_LANGUAGE =
            "/search/repositories?q=language:";
    private static final String SORT_AND_ORDER = "&sort=stars&order=desc";
    private static final String GITHUB_V3_MIME_TYPE = "application/vnd.github.v3+json";


    public String getTopProjectsByLanguage(String language) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", GITHUB_V3_MIME_TYPE);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<String> result = restTemplate.exchange(
                GITHUB_API_URL + GET_TOP_PROJECTS_BY_LANGUAGE + language + SORT_AND_ORDER,
                HttpMethod.GET,
                entity,
                String.class
        );

        return result.getBody();
    }
}
