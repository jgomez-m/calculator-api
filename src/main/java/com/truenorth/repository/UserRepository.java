package com.truenorth.repository;

import com.truenorth.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByStatus(User.Status status);
    Optional<User> findByUsername(String username);
}
