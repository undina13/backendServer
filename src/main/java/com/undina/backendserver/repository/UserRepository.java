package com.undina.backendserver.repository;

import com.undina.backendserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Transactional
    Optional<User> findByEmail(String email);
}
