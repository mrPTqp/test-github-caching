package com.learning.spring.githubapiwrapper.service;

import com.learning.spring.githubapiwrapper.entities.User;

import java.util.Optional;

public interface CustomerService {

    String login(String email, String password);

    Optional<User> findByToken(String token);

    User findByEmail(String email);

    User findById(Long id);
}
