package com.learning.spring.githubapiwrapper.controller;

import com.learning.spring.githubapiwrapper.entities.User;
import com.learning.spring.githubapiwrapper.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserProfileController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/api/users/user/{id}", produces = "application/json")
    public User getUserDetail(@PathVariable Long id) {
        return customerService.findById(id);
    }
}
