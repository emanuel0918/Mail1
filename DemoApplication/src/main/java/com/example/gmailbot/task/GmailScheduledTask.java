package com.example.gmailbot.task;

import com.example.gmailbot.service.GmailService;
import com.google.api.services.gmail.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class GmailScheduledTask {

    @Autowired
    private GmailService gmailService;

    @Scheduled(fixedRate = 60000) // 1 minute interval
    public void readEmails() {
        try {
            List<Message> messages = gmailService.listMessages("me");
            for (Message message : messages) {
                Message fullMessage = gmailService.getMessage("me", message.getId());
                System.out.println("Message snippet: " + fullMessage.getSnippet());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
