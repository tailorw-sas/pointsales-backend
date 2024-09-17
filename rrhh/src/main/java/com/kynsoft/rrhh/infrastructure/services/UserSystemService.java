package com.kynsoft.rrhh.infrastructure.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kynsoft.rrhh.domain.dto.CreateUserResponse;
import com.kynsoft.rrhh.domain.dto.CreateUserSystemRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@Configuration
public class UserSystemService {
    private static final Logger logger = LoggerFactory.getLogger(UserSystemService.class);
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    // URL del servicio remoto
    @Value("${user.system.api.url:http://localhost:9909/api/users}")
    private String userSystemApiUrl;

    public UserSystemService(HttpClient httpClient, ObjectMapper objectMapper) {
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
    }

    public String createUserSystem(CreateUserSystemRequest value) throws IOException, URISyntaxException, InterruptedException {
        String requestBody = objectMapper.writeValueAsString(value);
        logger.error("URL-USER:" + userSystemApiUrl);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(userSystemApiUrl))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("Error al crear usuario en el sistema. Código de estado: " + response.statusCode() + ", Respuesta: " + response.body());
        }
        if (response.statusCode() != 200) {
            throw new RuntimeException("Error al crear usuario en el sistema. Código de estado: " + response.statusCode() + ", Respuesta: " + response.body());
        }

        CreateUserResponse createUserResponse = objectMapper.readValue(response.body(), CreateUserResponse.class);

        return createUserResponse.getId();
    }
}