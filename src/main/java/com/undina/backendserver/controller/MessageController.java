package com.undina.backendserver.controller;

import com.undina.backendserver.dto.MessageDto;
import com.undina.backendserver.service.MessageService;
import com.undina.backendserver.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/messages")
@Tag(name = "Message Controller", description = "Реализует обмен сообщениями между пользователями")
public class MessageController {
    private final MessageService messageService;
    private final UserService userService;

    @Autowired
    public MessageController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }


    @Operation(
            summary = "Создание сообщения",
            description = "Позволяет создать сообщение"
    )
    @PostMapping
    @PreAuthorize("hasAuthority('user')")
    MessageDto create(@RequestBody MessageDto messageDto) {
        log.info("create message");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        long userId = userService.getUserEmail(auth.getName()).getId();
        return messageService.create(messageDto, userId);
    }

    @Operation(
            summary = "Список исходящих сообщений",
            description = "Пользователь может получить список его исходящих сообщений"
    )
    @GetMapping("/sender")
    @PreAuthorize("hasAuthority('user')")
    List<MessageDto> getAllBySender() {
        log.info("get all By Sender ");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        long userId = userService.getUserEmail(auth.getName()).getId();
        return messageService.getAllMessagesBySender(userId);
    }

    @Operation(
            summary = "Список входящих сообщений",
            description = "Пользователь может получить список его входящих сообщений"
    )
    @GetMapping("/recipient")
    @PreAuthorize("hasAuthority('user')")
    List<MessageDto> getAllByRecipient() {
        log.info("get all By Recipient ");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        long userId = userService.getUserEmail(auth.getName()).getId();
        return messageService.getAllMessagesBySender(userId);
    }

    @Operation(
            summary = "Список всех сообщений",
            description = "Пользователь может получить список всех своих сообщений - входящих и исходящих"
    )
    @GetMapping("/all")
    @PreAuthorize("hasAuthority('user')")
    List<MessageDto> getAllBySenderOrRecipient() {
        log.info("get all By Sender or Recipient ");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        long userId = userService.getUserEmail(auth.getName()).getId();
        return messageService.getAllMessagesBySenderOrRecipient(userId);
    }
}
