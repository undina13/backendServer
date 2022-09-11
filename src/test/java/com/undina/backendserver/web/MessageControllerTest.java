package com.undina.backendserver.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;

import static com.undina.backendserver.message.MessageTestData.messageDto1;
import static com.undina.backendserver.message.MessageTestData.messageDtoCreated;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class MessageControllerTest extends AbstractControllerTest {
    @Autowired
    ObjectMapper mapper;

    @Test
    @WithUserDetails(value = "user1rere@mail.ru")
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get("/messages/1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(messageDto1)));

    }

    @Test
    @DirtiesContext
    @WithUserDetails(value = "user1rere@mail.ru")
    void testCreate() throws Exception {
        perform(MockMvcRequestBuilders.post("/messages/")
                .content(mapper.writeValueAsString(messageDtoCreated))
                .characterEncoding(StandardCharsets.UTF_8)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(messageDtoCreated)));
    }
}