package com.github.mrptqp.githubapiwrapper.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.mrptqp.githubapiwrapper.entities.GitRepository;

import java.util.List;

public interface CacheService {

    List<GitRepository> getTopProjectsByLanguage(String queryLanguage) throws JsonProcessingException;
}
