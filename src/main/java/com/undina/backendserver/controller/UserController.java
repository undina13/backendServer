package com.undina.backendserver.controller;

import com.undina.backendserver.dto.UserDto;
import com.undina.backendserver.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    UserDto create(@RequestBody UserDto userDto) {
        log.info("create user");
        return userService.create(userDto);
    }

    @PatchMapping("/{userId}")
    @PreAuthorize("hasAuthority('admin')")
    public UserDto update(@PathVariable Long userId, @RequestBody UserDto userDto) {
        log.info("update user id ={}", userId);
        return userService.update(userId, userDto);
    }

    @DeleteMapping("/{userId}")
  @PreAuthorize("hasAuthority('admin')")
    public void delete(@PathVariable Long userId) {
        log.info("delete user id={}", userId);
        userService.delete(userId);
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasAuthority('user')")
    public UserDto getUserById(@PathVariable Long userId) {
        log.info("get user id={}", userId);
        return userService.getUserById(userId);
    }

    @GetMapping
   @PreAuthorize("hasAuthority('user')")
    public List<UserDto> getAllUsers() {
        log.info("get all users");
        return userService.getAllUsers();
    }
}
