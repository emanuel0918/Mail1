package com.example.gmailbot.task;

import com.example.gmailbot.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GmailScheduledTask {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TokenService tokenService;

    @Scheduled(fixedRate = 6000) // 6 seconds interval
    public void readEmails() {
        logger.info("hoolaa1");
        logger.debug("holaaa2");
        System.out.println("holaa");
        try {
            // Log the token
            String accessToken = tokenService.getAccessToken();
            System.out.println("Logged Token: " + accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
