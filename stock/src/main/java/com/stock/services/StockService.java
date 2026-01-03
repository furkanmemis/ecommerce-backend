package com.stock.services;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import com.stock.models.Product;
import java.util.List;

@Service
public class StockService {

    private final SimpMessagingTemplate messagingTemplate;

    public StockService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void pushStock(List<Product> products) {
        messagingTemplate.convertAndSend("/stock-topic/stock", products);
    }
}
