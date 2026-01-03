package com.stock.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class StockSocketController {

    @MessageMapping("/stock")
    @SendTo("/stock-topic/stock")
    public String sendStock(String message) {
        return message;
    }
}
