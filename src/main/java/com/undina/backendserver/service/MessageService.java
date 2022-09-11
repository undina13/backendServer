package com.undina.backendserver.service;

import com.undina.backendserver.dto.MessageDto;
import com.undina.backendserver.exception.UserNotFoundException;
import com.undina.backendserver.mapper.MessageMapper;
import com.undina.backendserver.model.Message;
import com.undina.backendserver.model.User;
import com.undina.backendserver.repository.MessageRepository;
import com.undina.backendserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public MessageDto create(MessageDto messageDto, Long senderId) {
        Message message = MessageMapper.toMessage(messageDto);
        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new UserNotFoundException("sender not found"));
        User recipient = userRepository.findById(messageDto.getRecipient())
                .orElseThrow(() -> new UserNotFoundException("sender not found"));
        message.setSender(sender);
        message.setRecipient(recipient);
        message.setTime(LocalDateTime.now());
        return MessageMapper.toMessageDto(messageRepository.save(message));
    }

    public List<MessageDto> getAllMessagesBySender(Long senderId) {
        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new UserNotFoundException("sender not found"));
        return messageRepository.findAllBySender(sender)
                .stream()
                .map(MessageMapper::toMessageDto)
                .collect(Collectors.toList());
    }

    public List<MessageDto> getAllMessagesByRecipient(Long recipientId) {
        User recipient = userRepository.findById(recipientId)
                .orElseThrow(() -> new UserNotFoundException("sender not found"));
        return messageRepository.findAllByRecipient(recipient)
                .stream()
                .map(MessageMapper::toMessageDto)
                .collect(Collectors.toList());
    }

    public List<MessageDto> getAllMessagesBySenderOrRecipient(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("sender not found"));
        return messageRepository.findAllBySenderOrRecipient(user, user)
                .stream()
                .map(MessageMapper::toMessageDto)
                .collect(Collectors.toList());
    }
}
