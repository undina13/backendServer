package com.undina.backendserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.undina.backendserver.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
