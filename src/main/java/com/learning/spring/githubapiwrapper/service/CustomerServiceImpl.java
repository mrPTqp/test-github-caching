package com.learning.spring.githubapiwrapper.service;

import com.learning.spring.githubapiwrapper.entities.User;
import com.learning.spring.githubapiwrapper.exceptions.UnauthorizedException;
import com.learning.spring.githubapiwrapper.exceptions.UserAlreadyExistException;
import com.learning.spring.githubapiwrapper.exceptions.UserNotFoundException;
import com.learning.spring.githubapiwrapper.repo.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service("customerService")
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder encoder;

    @Override
    public void saveUser(String email, String password) {
        String encodedPassword = encoder.encode(password);

        customerRepository.findByEmail(email)
                .ifPresent(u -> {
                    throw new UserAlreadyExistException("User already exists! Choose a different name.");
                });

        User user = new User();
        user.setEmail(email);
        user.setPassword(encodedPassword);
        customerRepository.save(user);

        String token = this.login(email, encodedPassword);
        user.setToken(token);
        LocalDateTime expireDate = LocalDateTime.now().plusHours(24);
        user.setExpireDate(expireDate);
        customerRepository.save(user);
    }

    @Override
    public User findById(Long id) {

        return customerRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found. Please check your id"));
    }

    @Override
    public String login(String email, String password) {
        User customer = customerRepository.findByEmail(email).get();
        String savedPassword = customer.getPassword();

        if (!encoder.matches(password, savedPassword)) {
            throw new UnauthorizedException("Password is incorrect, try again.");
        }

        return customerRepository.login(email, savedPassword).map(user -> {
            String existingToken = user.getToken();

            if (existingToken != null) {
                LocalDateTime expireDate = user.getExpireDate();

                if (expireDate.isAfter(LocalDateTime.now())) {
                    return existingToken;
                }
            }

            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setExpireDate(LocalDateTime.now().plusHours(24));
            customerRepository.save(user);

            return token;
        })
                .orElseThrow(() -> new UserNotFoundException("User not found. Please check your login and password"));
    }

}