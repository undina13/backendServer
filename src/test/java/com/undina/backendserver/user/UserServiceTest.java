package com.undina.backendserver.user;

import com.undina.backendserver.dto.UserDto;
import com.undina.backendserver.exception.UserNotFoundException;
import com.undina.backendserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import static com.undina.backendserver.user.UserTestData.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@Transactional
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    @DirtiesContext
    void testCreate() {
        userService.create(userDtoCreated);
        List<UserDto> users = userService.getAllUsers();
        assertThat(users.size(), equalTo(4));
        assertThat(users, equalTo(List.of(userDto1, userDto2, userDto3, userDtoCreated)));
    }

    @Test
    void testGetAll() {
        List<UserDto> users = userService.getAllUsers();
        assertThat(users.size(), equalTo(3));
        assertThat(users, equalTo(List.of(userDto1, userDto2, userDto3)));
    }

    @Test
    @DirtiesContext
    void testUpdateUser() {
        userDto2.setName("User2new");
        UserDto userDtoFromSQL = userService.update(2L, userDto2);
        assertThat(userDtoFromSQL, equalTo(userDto2));
        userDto2.setName("user2");
    }

    @Test
    void testUpdateUserNotFound() {
        assertThrows(UserNotFoundException.class, () -> userService.update(20L, userDto2));
    }

    @Test
    @DirtiesContext
    void testUpdateUserEmail() {
        userDto2.setEmail("User2new@mail.ru");
        UserDto userDtoFromSQL = userService.update(2L, userDto2);
        assertThat(userDtoFromSQL, equalTo(userDto2));
        userDto2.setEmail("user1rere@mail.ru");
    }

    @Test
    @DirtiesContext
    void testUpdateUserEmailName() {
        userDto2.setName(null);
        userDto2.setEmail(null);
        UserDto userDtoFromSQL = userService.update(2L, userDto2);
        userDto2.setEmail("user1rere@mail.ru");
        userDto2.setName("user2");
       assertThat(userDtoFromSQL, equalTo(userDto2));

    }

    @Test
    @DirtiesContext
    void testDeleteUser() {
        userService.delete(1L);
        assertThat(userService.getAllUsers(), equalTo(List.of(userDto2, userDto3)));
    }

    @Test
    void testGetUserById() {
        UserDto userDtoFromSQL = userService.getUserById(1L);
        assertThat(userDtoFromSQL, equalTo(userDto1));
    }

    @Test
    void testGetUserByWrongId() {
        assertThrows(UserNotFoundException.class, () -> userService.getUserById(100L));
    }

    @Test
    void testDeleteWrongId() {
        assertThrows(UserNotFoundException.class, () -> userService.delete(100L));
    }


}
