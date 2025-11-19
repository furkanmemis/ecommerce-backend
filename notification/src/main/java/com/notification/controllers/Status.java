package com.notification.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class Status {

    @GetMapping("/health")
    public String health() {
        return "Notification service is running!";
    }

}
