package com.learning.spring.githubapiwrapper.service;

import com.learning.spring.githubapiwrapper.entities.User;

public interface CustomerService {

    void saveUser(String email, String password);

    User findById(Long id);

    String login(String email, String password);
}
