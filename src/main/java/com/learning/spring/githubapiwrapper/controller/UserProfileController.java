package com.learning.spring.githubapiwrapper.controller;

import com.learning.spring.githubapiwrapper.entities.User;
import com.learning.spring.githubapiwrapper.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserProfileController {

    private final CustomerService customerService;

    @GetMapping(value = "/api/users/{id}", produces = "application/json")
    public User getUserDetail(@PathVariable Long id) {
        return customerService.findById(id);
    }
}
