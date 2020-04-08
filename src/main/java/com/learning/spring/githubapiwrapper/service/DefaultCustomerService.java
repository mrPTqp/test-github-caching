package com.learning.spring.githubapiwrapper.service;

import com.learning.spring.githubapiwrapper.entities.User;
import com.learning.spring.githubapiwrapper.repo.CustomerRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service("customerService")
public class DefaultCustomerService implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public String login(String email, String password) {
        Optional<User> customer = customerRepository.login(email, password);
        if (customer.isPresent()) {
            User custom = customer.get();
            String existingToken = custom.getToken();

            if (existingToken != null) {
                Date expireDate = custom.getExpireDate();

                if (expireDate.after(new Date())) {
                    return existingToken;
                }
            }

            String token = UUID.randomUUID().toString();
            custom.setToken(token);
            custom.setExpireDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10));
            customerRepository.save(custom);
            return token;
        }

        return StringUtils.EMPTY;
    }

    @Override
    public Optional<User> findByToken(String token) {
        Optional<User> customer = customerRepository.findByToken(token);
        if (customer.isPresent()) {
            User user1 = customer.get();
            User user = new User();
            user.setEmail(user1.getEmail());
            user.setPassword(user1.getPassword());

            return Optional.of(user);
        }
        return Optional.empty();
    }

    @Override
    public User findByEmail(String email) {
        Optional<User> customer = customerRepository.findByEmail(email);
        return customer.orElse(null);
    }

    @Override
    public User findById(Long id) {
        Optional<User> customer = customerRepository.findById(id);
        return customer.orElse(null);
    }
}
