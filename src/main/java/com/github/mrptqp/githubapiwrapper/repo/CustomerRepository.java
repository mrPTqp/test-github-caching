package com.github.mrptqp.githubapiwrapper.repo;

import com.github.mrptqp.githubapiwrapper.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<User, Long> {

    @Query(value = "SELECT u FROM User u where u.email = ?1 and u.password = ?2 ")
    Optional<User> login(String email, String password);

    Optional<User> findByToken(String token);

    Optional<User> findByEmail(String email);

    Optional<User> findById(Long id);
}
