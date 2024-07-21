package com.example.gmailbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GmailBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(GmailBotApplication.class, args);
    }
}
