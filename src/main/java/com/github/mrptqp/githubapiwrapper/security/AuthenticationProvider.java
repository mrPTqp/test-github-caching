package com.github.mrptqp.githubapiwrapper.security;

import com.github.mrptqp.githubapiwrapper.entities.User;
import com.github.mrptqp.githubapiwrapper.repo.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    private final CustomerRepository customerRepository;

    @Override
    protected void additionalAuthenticationChecks(
            UserDetails userDetails,
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
    ) throws AuthenticationException {
        //
    }

    @Override
    protected UserDetails retrieveUser(
            String email,
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
    ) throws AuthenticationException {

        Object token = usernamePasswordAuthenticationToken.getCredentials();

        User user = Optional
                .ofNullable(token)
                .map(String::valueOf)
                .flatMap(customerRepository::findByToken)
                .orElseThrow(() -> new UsernameNotFoundException("Cannot find user with authentication token=" + token));

        return new MyUserDetails(user);
    }
}