package com.github.mrptqp.githubapiwrapper.service;

import com.github.mrptqp.githubapiwrapper.entities.User;

public interface CustomerService {

    void saveUser(String email, String password);

    User findById(Long id);

    String login(String email, String password);
}
