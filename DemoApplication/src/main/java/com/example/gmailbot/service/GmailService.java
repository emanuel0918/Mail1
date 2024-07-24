package com.example.gmailbot.service;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GmailService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${google.gmail.users.url}")
    private String BASE_URL;
    private static final OkHttpClient client = new OkHttpClient();

    public List<String> listMessages(String accessToken) throws IOException {
        String url = BASE_URL + "/messages";
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + accessToken)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            String responseBody = response.body().string();
            JSONObject jsonResponse = new JSONObject(responseBody);
            JSONArray messages = jsonResponse.getJSONArray("messages");

            List<String> messageIds = new ArrayList<>();
            for (int i = 0; i < messages.length(); i++) {
                messageIds.add(messages.getJSONObject(i).getString("id"));
            }

            return messageIds;
        }
    }

    public String getMessage(String accessToken, String messageId) throws IOException {
        String url = BASE_URL + "/messages/" + messageId;
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + accessToken)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            String responseBody = response.body().string();
            JSONObject jsonResponse = new JSONObject(responseBody);
            return jsonResponse.getString("snippet");
        }
    }
}
