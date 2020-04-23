package com.github.mrptqp.githubapiwrapper.controller;

import com.github.mrptqp.githubapiwrapper.service.CustomerService;
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