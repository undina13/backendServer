package com.undina.backendserver.controller;

import com.undina.backendserver.dto.MessageDto;
import com.undina.backendserver.model.User;
import com.undina.backendserver.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/messages")
public class MessageController {
    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('user')")
    MessageDto create(@RequestBody MessageDto messageDto) {
        log.info("create message");
        User sender = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return messageService.create(messageDto, sender.getId());
    }

    @GetMapping("/sender")
    @PreAuthorize("hasAuthority('user')")
    List<MessageDto> getAllBySender() {
        log.info("get all By Sender ");
        User sender = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return messageService.getAllMessagesBySender(sender.getId());
    }

    @GetMapping("/recipient")
    @PreAuthorize("hasAuthority('user')")
    List<MessageDto> getAllByRecipient() {
        log.info("get all By Recipient ");
        User recipient = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return messageService.getAllMessagesBySender(recipient.getId());
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('user')")
    List<MessageDto> getAllBySenderOrRecipient() {
        log.info("get all By Sender or Recipient ");
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return messageService.getAllMessagesBySenderOrRecipient(user.getId());
    }
}
