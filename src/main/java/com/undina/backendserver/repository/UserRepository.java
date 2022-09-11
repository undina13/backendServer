package com.undina.backendserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.undina.backendserver.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
