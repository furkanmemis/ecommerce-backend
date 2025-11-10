package com.authentication.controllers;

import java.util.UUID;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.authentication.services.UserService;
import com.authentication.models.User;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService service) {
        this.userService = service;
    }

    @PostMapping
    @Operation(summary = "Create a new user")
    public User CreateUser(@RequestBody User user) {
        return userService.CreateUser(user);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a user by UUID")
    public Optional<User> GetUserWithId(@PathVariable UUID id) {
        return userService.GetUserWithId(id);
    }

    @GetMapping
    @Operation(summary = "Get all users")
    public List<User> GetAll() {
        return this.userService.GetAll();
    }

}
