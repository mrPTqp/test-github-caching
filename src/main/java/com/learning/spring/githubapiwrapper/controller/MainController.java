package com.learning.spring.githubapiwrapper.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.learning.spring.githubapiwrapper.entities.GitRepository;
import com.learning.spring.githubapiwrapper.service.CacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainController {
    @Autowired
    private final CacheService cacheService;

    @GetMapping("api/topProjectsByLanguage")
    public List<GitRepository> getTopProjectsByLanguage(@RequestParam String language) throws JsonProcessingException {
        return cacheService.getTopProjectsByLanguage(language);
    }
}
