package com.learning.spring.githubapiwrapper.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.learning.spring.githubapiwrapper.entities.GitRepository;

import java.util.List;

public interface CacheService {

    List<GitRepository> getTopProjectsByLanguage(String queryLanguage) throws JsonProcessingException;
}
