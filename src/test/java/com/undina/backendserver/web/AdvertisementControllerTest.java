package com.undina.backendserver.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.undina.backendserver.advertisement.AdwertismentTestData.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class AdvertisementControllerTest extends AbstractControllerTest {
    @Autowired
    ObjectMapper mapper;

    @Test
    @WithUserDetails(value = "user1rere@mail.ru")
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get("/advertisements/1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(advertisementDto1)));

    }

    @Test
    @WithUserDetails(value = "user1rere@mail.ru")
    void testUpdate() throws Exception {
        perform(MockMvcRequestBuilders.patch("/advertisements/1")
                .content(mapper.writeValueAsString(advertisementDto1))
                .characterEncoding(StandardCharsets.UTF_8)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(advertisementDto1)));
    }

    @Test
    @WithUserDetails(value = "user1rere@mail.ru")
    void testGetAll() throws Exception {
        perform(MockMvcRequestBuilders.get("/advertisements/"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(List.of(advertisementDto1,
                        advertisementDto2, advertisementDto3))));
    }

    @Test
    @WithUserDetails(value = "user1rere@mail.ru")
    void testGetByStatusActive() throws Exception {
        perform(MockMvcRequestBuilders.get("/advertisements/active"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(List.of(advertisementDto1,
                        advertisementDto3))));
    }

    @Test
    @WithUserDetails(value = "user1rere@mail.ru")
    void testGetAllByUser() throws Exception {
        perform(MockMvcRequestBuilders.get("/advertisements/user/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(List.of(advertisementDto1,
                        advertisementDto2))));
    }
}