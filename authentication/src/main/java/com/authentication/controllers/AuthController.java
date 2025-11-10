package com.authentication.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.authentication.services.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.authentication.dto.LoginRequest;
import io.swagger.v3.oas.annotations.Operation;
import com.authentication.dto.LoginResponse;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/login")
    @Operation(description = "Login with email and password")
    public LoginResponse Login(@RequestBody LoginRequest request) {
       return authService.Login(request.getEmail(), request.getPassword());
    }

}
