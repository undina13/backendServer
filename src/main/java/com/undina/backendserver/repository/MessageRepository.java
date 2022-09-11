package com.undina.backendserver.repository;

import com.undina.backendserver.model.Message;
import com.undina.backendserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllBySender(User sender);

    List<Message> findAllByRecipient(User recipient);

    List<Message> findAllBySenderOrRecipient(User sender, User recipient);

}
