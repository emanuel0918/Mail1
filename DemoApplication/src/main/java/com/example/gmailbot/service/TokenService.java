package com.example.gmailbot.service;

import okhttp3.*;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class TokenService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${google.url}")
    private String url;

    @Value("${google.refresh.token}")
    private String refreshToken;

    private String clientId;
    private String clientSecret;

    public TokenService() throws IOException {
        // Load credentials from the JSON file
        ClassPathResource resource = new ClassPathResource("credentials.json");
        String jsonContent = new String(Files.readAllBytes(Paths.get(resource.getURI())));
        JSONObject jsonObject = new JSONObject(jsonContent);

        this.clientId = jsonObject.getString("client_id");
        this.clientSecret = jsonObject.getString("client_secret");
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
            logger.info("Access Token: " + responseBody);
            return responseBody;
        }
    }
}
