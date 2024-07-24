package com.example.gmailbot.task;

import com.example.gmailbot.service.GmailService;
import com.example.gmailbot.service.TokenService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class GmailScheduledTask {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TokenService tokenService;

    @Autowired
    private GmailService gmailService;

    @Scheduled(fixedRate = 6000) // 6 seconds interval
    public void readEmails() {
        logger.info("hoolaa1");
        logger.debug("holaaa2");
        System.out.println("holaa");
        try {
            // Log the token
            String accessTokenJson = tokenService.getAccessToken();
            JSONObject jsonObject = new JSONObject(accessTokenJson);
            String accessToken = jsonObject.getString("access_token");

            // Fetch and log the email snippets
            List<String> messageIds = gmailService.listMessages(accessToken);
            for (String messageId : messageIds) {
                String snippet = gmailService.getMessage(accessToken, messageId);
                logger.info("Email Snippet: " + snippet);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
