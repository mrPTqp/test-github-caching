package com.learning.spring.githubapiwrapper.controller;

import com.learning.spring.githubapiwrapper.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthorizationController {

    private final CustomerService customerService;

    @PostMapping("/login")
    public String getToken(@RequestParam("email") final String email, @RequestParam("password") final String password) {
        return customerService.login(email, password);
    }
}