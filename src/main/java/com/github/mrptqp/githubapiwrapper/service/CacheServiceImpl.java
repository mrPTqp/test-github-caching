package com.github.mrptqp.githubapiwrapper.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mrptqp.githubapiwrapper.entities.GitRepository;
import com.github.mrptqp.githubapiwrapper.entities.SearchResponse;
import com.github.mrptqp.githubapiwrapper.interrogators.GithubInterrogator;
import com.github.mrptqp.githubapiwrapper.repo.CacheRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CacheServiceImpl implements CacheService {

    private final GithubInterrogator githubInterrogator;

    private final CacheRepository cacheRepository;

    private final ObjectMapper objectMapper;

    private final static int REPO_RETENTION_MINUTES = 60;

    @Override
    @Transactional
    public List<GitRepository> getTopProjectsByLanguage(String queryLanguage) throws JsonProcessingException {

        List<GitRepository> cachedRepositories = cacheRepository.findByQueryNameAndQueryLanguage(
                "topProjectsByLanguage=",
                queryLanguage
        );

        if (cachedRepositories.isEmpty()) {
            return updateGitRepositories(queryLanguage);
        } else {
            for (GitRepository gitRepository : cachedRepositories) {
                if (gitRepository.getTimestamp().plusMinutes(REPO_RETENTION_MINUTES).isBefore(LocalDateTime.now())) {
                    cacheRepository.deleteAllByQueryNameAndQueryLanguage(
                            "topProjectsByLanguage=",
                            queryLanguage
                    );

                    return updateGitRepositories(queryLanguage);
                }
            }
            return cacheRepository.findByQueryNameAndQueryLanguage("topProjectsByLanguage=", queryLanguage);
        }
    }

    private List<GitRepository> updateGitRepositories(String queryLanguage) throws JsonProcessingException {
        String topProjectsByLanguage = githubInterrogator.getTopProjectsByLanguage(queryLanguage);
        LocalDateTime timestamp = LocalDateTime.now();

        List<GitRepository> gitRepositories = objectMapper
                .readValue(topProjectsByLanguage, SearchResponse.class)
                .getItems();

        gitRepositories
                .stream()
                .map(gitRepository -> gitRepository
                        .toBuilder()
                        .queryName("topProjectsByLanguage=")
                        .queryLanguage(queryLanguage)
                        .timestamp(timestamp).build())
                .forEach(cacheRepository::save);

        return cacheRepository.findByQueryNameAndQueryLanguage("topProjectsByLanguage=", queryLanguage);
    }

}
