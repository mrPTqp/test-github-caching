package com.learning.spring.githubapiwrapper.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.spring.githubapiwrapper.entities.GitRepository;
import com.learning.spring.githubapiwrapper.entities.SearchResponse;
import com.learning.spring.githubapiwrapper.interrogators.GithubInterrogator;
import com.learning.spring.githubapiwrapper.repo.CacheRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CacheService {
    @Autowired
    private final GithubInterrogator githubInterrogator;
    @Autowired
    private final CacheRepository cacheRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final static int THRESHOLD = 1000 * 5;

    @Transactional
    public List<GitRepository> getTopProjectsByLanguage(String language) throws JsonProcessingException {

        List<GitRepository> gitRepositoriesFromDB = cacheRepository.findByQueryName("topProjectsByLanguage=" + language);

        if (gitRepositoriesFromDB.isEmpty()) {
            return updateGitRepositories(language);

        } else {
            for (GitRepository gitRepository : gitRepositoriesFromDB) {
                if (new Date().getTime() - gitRepository.getTimestamp().getTime() > THRESHOLD) {
                    clearTable(language);
                    return updateGitRepositories(language);
                }
            }
            return cacheRepository.findByQueryName("topProjectsByLanguage=" + language);
        }
    }

    private void clearTable(String language) {
        cacheRepository.deleteAllByQueryName("topProjectsByLanguage=" + language);
    }

    private List<GitRepository> updateGitRepositories(String language) throws JsonProcessingException {
        String topProjectsByLanguage = githubInterrogator.getTopProjectsByLanguage(language);

        List<GitRepository> gitRepositories = objectMapper
                .readValue(topProjectsByLanguage, SearchResponse.class)
                .getItems();

        for (GitRepository gitRepository : gitRepositories) {
            gitRepository.setQueryName("topProjectsByLanguage=" + language);
            gitRepository.setTimestamp(new Date());
            cacheRepository.save(gitRepository);
        }

        return cacheRepository.findByQueryName("topProjectsByLanguage=" + language);
    }
}
