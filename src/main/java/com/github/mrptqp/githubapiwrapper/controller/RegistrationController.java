package com.github.mrptqp.githubapiwrapper.controller;

import com.github.mrptqp.githubapiwrapper.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegistrationController {

    private final CustomerService customerService;

    @PostMapping("/registration")
    public String addUser(@RequestParam("email") final String email, @RequestParam("password") final String password) {

        customerService.saveUser(email, password);

        return "User was created successfully.";
    }


}
