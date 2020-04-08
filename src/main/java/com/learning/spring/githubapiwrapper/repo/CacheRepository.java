package com.learning.spring.githubapiwrapper.repo;

import com.learning.spring.githubapiwrapper.entities.GitRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CacheRepository extends CrudRepository<GitRepository, String> {
    @Query(value = "SELECT r FROM GitRepository r where r.queryName = ?1")
    List<GitRepository> findByQueryName(String queryName);

    void deleteAllByQueryName(String queryName);
}
