package com.notification.services;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.apache.kafka.clients.consumer.*;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;

@Service
public class EmailService {

    private KafkaConsumer<String, String> consumer;
    private JavaMailSender mailSender;
  

    public EmailService(JavaMailSender mailSender) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "group-1");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        this.consumer = new KafkaConsumer<>(props);
        this.mailSender = mailSender;
    }

    public void SentMail(String to) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject("Test Mail");
        mailMessage.setText("Hello! Welcome Ecommerce "+ to);
        mailSender.send(mailMessage);
    }

    @PostConstruct
    public void ListenEmail() {
        consumer.subscribe(Collections.singletonList("email-notifications"));

        System.out.println("Listening messages...");

        new Thread(() -> {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records) {
                    SentMail(record.value());
                    System.out.println("Email sent to "+ record.value());
                }
            }
        }).start();
    }

}
