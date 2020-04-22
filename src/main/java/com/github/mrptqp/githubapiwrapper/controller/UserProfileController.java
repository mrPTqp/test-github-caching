package com.github.mrptqp.githubapiwrapper.controller;

import com.github.mrptqp.githubapiwrapper.entities.User;
import com.github.mrptqp.githubapiwrapper.service.CustomerService;
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
