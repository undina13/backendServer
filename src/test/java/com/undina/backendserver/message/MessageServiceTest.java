package com.undina.backendserver.message;

import com.undina.backendserver.dto.MessageDto;
import com.undina.backendserver.exception.UserNotFoundException;
import com.undina.backendserver.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.undina.backendserver.message.MessageTestData.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@Transactional
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MessageServiceTest {

    @Autowired
    private MessageService messageService;

    @Test
    @DirtiesContext
    void testCreate() {
        messageService.create(messageDtoCreated, 2L);
        List<MessageDto> messages = messageService.getAllMessagesBySender(2L);
        assertThat(messages.size(), equalTo(2));
        assertThat(messages, equalTo(List.of(messageDto2, messageDtoCreated)));
    }

    @Test
    void testCreateWrongId() {
        assertThrows(UserNotFoundException.class, () -> messageService.getAllMessagesBySender(200L));
    }

    @Test
    void testGetAllBySender() {
        List<MessageDto> messages = messageService.getAllMessagesBySender(1L);
        assertThat(messages.size(), equalTo(2));
        assertThat(messages, equalTo(List.of(messageDto1, messageDto3)));
    }

    @Test
    void testGetAllByRecipient() {
        List<MessageDto> messages = messageService.getAllMessagesByRecipient(2L);
        assertThat(messages.size(), equalTo(2));
        assertThat(messages, equalTo(List.of(messageDto1, messageDto3)));
    }

    @Test
    void testGetAllBySenderOrRecipient() {
        List<MessageDto> messages = messageService.getAllMessagesBySenderOrRecipient(2L);
        assertThat(messages.size(), equalTo(3));
        assertThat(messages, equalTo(List.of(messageDto1, messageDto2, messageDto3)));
    }


}
