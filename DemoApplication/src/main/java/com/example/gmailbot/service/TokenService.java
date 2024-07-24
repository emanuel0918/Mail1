package com.example.gmailbot.service;

import okhttp3.*;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class TokenService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${google.token.url}")
    private String url;

    @Value("${google.refresh.token}")
    private String refreshToken;

    private String clientId;
    private String clientSecret;

    @PostConstruct
    public void init() throws IOException {
        // Load credentials from the JSON file at the absolute path
        String jsonContent = new String(Files.readAllBytes(Paths.get("/opt/app/credentials.json")));
        JSONObject jsonObject = new JSONObject(jsonContent);
        JSONObject web = jsonObject.getJSONObject("web");

        this.clientId = web.getString("client_id");
        this.clientSecret = web.getString("client_secret");
    }

    public String getAccessToken() throws IOException {
        RequestBody formBody = new FormBody.Builder()
                .add("client_id", clientId)
                .add("client_secret", clientSecret)
                .add("refresh_token", refreshToken)
                .add("grant_type", "refresh_token")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        OkHttpClient client = new OkHttpClient();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            String responseBody = response.body().string();
            return responseBody;
        }
    }
}
