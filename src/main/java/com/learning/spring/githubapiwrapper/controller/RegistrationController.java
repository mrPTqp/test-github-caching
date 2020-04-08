package com.learning.spring.githubapiwrapper.controller;

import com.learning.spring.githubapiwrapper.entities.User;
import com.learning.spring.githubapiwrapper.repo.CustomerRepository;
import com.learning.spring.githubapiwrapper.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class RegistrationController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/registration")
    public String addUser(@RequestParam("email") final String email, @RequestParam("password") final String password) {
        User userFromDB = customerService.findByEmail(email);

        if (userFromDB != null) {
            return "User already exists! Choose a different name.";
        }


        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        customerRepository.save(user);

        String token = customerService.login(email, password);
        user.setToken(token);
        Date expireDate = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10);
        user.setExpireDate(expireDate);

        customerRepository.save(user);

        return "User was created successfully.";
    }
}
