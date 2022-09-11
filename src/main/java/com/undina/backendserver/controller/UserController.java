package com.undina.backendserver.controller;

import com.undina.backendserver.dto.UserDto;
import com.undina.backendserver.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/users")
@Tag(name = "User Controller", description = "Отвечает за пользователей")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(
            summary = "Создание пользователя",
            description = "Позволяет создать пользователя"
    )
    @PostMapping
    UserDto create(@RequestBody UserDto userDto) {
        log.info("create user");
        return userService.create(userDto);
    }

    @Operation(
            summary = "Изменение пользователя",
            description = "Позволяет изменить пользователя"
    )
    @PatchMapping("/{userId}")
    @PreAuthorize("hasAuthority('admin')")
    public UserDto update(@PathVariable Long userId, @RequestBody UserDto userDto) {
        log.info("update user id ={}", userId);
        return userService.update(userId, userDto);
    }

    @Operation(
            summary = "Удаление пользователя",
            description = "Позволяет удалить пользователя"
    )
    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority('admin')")
    public void delete(@PathVariable Long userId) {
        log.info("delete user id={}", userId);
        userService.delete(userId);
    }

    @Operation(
            summary = "Получение пользователя",
            description = "Позволяет получить пользователя по Id"
    )
    @GetMapping("/{userId}")
    @PreAuthorize("hasAuthority('user')")
    public UserDto getUserById(@PathVariable Long userId) {
        log.info("get user id={}", userId);
        return userService.getUserById(userId);
    }

    @Operation(
            summary = "Получение всех пользователей",
            description = "Получение списка всех пользователей"
    )
    @GetMapping
    @PreAuthorize("hasAuthority('user')")
    public List<UserDto> getAllUsers() {
        log.info("get all users");
        return userService.getAllUsers();
    }
}
