package com.github.mrptqp.githubapiwrapper.repo;

import com.github.mrptqp.githubapiwrapper.entities.GitRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CacheRepository extends CrudRepository<GitRepository, String> {

    List<GitRepository> findByQueryNameAndQueryLanguage(String queryName, String queryLanguage);

    void deleteAllByQueryNameAndQueryLanguage(String queryName, String queryLanguage);
}
