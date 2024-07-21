package com.example.gmailbot.service;

import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class GmailService {

    @Autowired
    private Gmail gmail;

    public List<Message> listMessages(String userId) throws IOException {
        ListMessagesResponse response = gmail.users().messages().list(userId).execute();
        return response.getMessages();
    }

    public Message getMessage(String userId, String messageId) throws IOException {
        return gmail.users().messages().get(userId, messageId).execute();
    }
}
