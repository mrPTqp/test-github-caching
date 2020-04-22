package com.github.mrptqp.githubapiwrapper.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.mrptqp.githubapiwrapper.entities.GitRepository;
import com.github.mrptqp.githubapiwrapper.service.CacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GithubAccessController {

    private final CacheService cacheService;

    @GetMapping("api/topProjectsByLanguage")
    public List<GitRepository> getTopProjectsByLanguage(@RequestParam String language) throws JsonProcessingException {
        return cacheService.getTopProjectsByLanguage(language);
    }
}
