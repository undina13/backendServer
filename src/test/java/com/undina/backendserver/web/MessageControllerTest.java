package com.undina.backendserver.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.undina.backendserver.message.MessageTestData.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class MessageControllerTest extends AbstractControllerTest {
    @Autowired
    ObjectMapper mapper;


    @Test
    @WithUserDetails(value = "user1rere@mail.ru")
    @DirtiesContext
    void testCreate() throws Exception {
        perform(MockMvcRequestBuilders.post("/messages")
                .content(mapper.writeValueAsString(messageDtoCreated))
                .characterEncoding(StandardCharsets.UTF_8)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    @WithUserDetails(value = "user1rere@mail.ru")
    void testGetAllBySender() throws Exception {
        perform(MockMvcRequestBuilders.get("/messages/sender"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(List.of(messageDto2))));
    }

    @Test
    @WithUserDetails(value = "user1rere@mail.ru")
    void testGetAllBySenderOrRecipient() throws Exception {
        perform(MockMvcRequestBuilders.get("/messages/all"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(List.of(messageDto1, messageDto2, messageDto3))));
    }

    @Test
    @WithUserDetails(value = "user1rere@mail.ru")
    void testGetAllByRecipient() throws Exception {
        perform(MockMvcRequestBuilders.get("/messages/recipient"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(List.of(messageDto2))));
    }

}