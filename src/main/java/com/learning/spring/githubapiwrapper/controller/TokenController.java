package com.learning.spring.githubapiwrapper.controller;

import com.learning.spring.githubapiwrapper.service.CustomerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/login")
    public String getToken(@RequestParam("email") final String email, @RequestParam("password") final String password) {
        String token = customerService.login(email, password);
        if (StringUtils.isEmpty(token)) {
            return "User not found";
        }
        return token;
    }
}