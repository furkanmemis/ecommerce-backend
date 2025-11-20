package com.notification.services;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.apache.kafka.clients.consumer.*;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

@Service
public class EmailService {

    private KafkaConsumer<String, String> consumer;

    public EmailService() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "group-1");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        this.consumer = new KafkaConsumer<>(props);
    }

    @PostConstruct
    public void ListenEmail() {
        consumer.subscribe(Collections.singletonList("email-notifications"));

        System.out.println("Listening messages...");

        new Thread(() -> {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println("Kafka producer key: " + record.key() + " value:" + record.value() + " topic: "
                            + record.topic());
                }
            }
        }).start();
    }

}
