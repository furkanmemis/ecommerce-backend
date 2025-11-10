package com.authentication.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Status {

    @GetMapping("/health")
    public String health() {
        return "Authentication service is running!";
    }
}
