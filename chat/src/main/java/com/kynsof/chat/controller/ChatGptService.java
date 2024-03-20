package com.kynsof.chat.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ChatGptService {
    private final WebClient webClient;

    public ChatGptService(@Value("${openai.api.key}") String apiKey) {
        this.webClient = WebClient.builder()
                .baseUrl("https://api.openai.com/v1")
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .build();
    }

    public Mono<String> obtenerRespuesta(String contextoCompleto) {
        return this.webClient.post()
                .uri("/engines/davinci/completions")
                .bodyValue(crearPayload(contextoCompleto))
                .retrieve()
                .bodyToMono(String.class);
    }

    private String crearPayload(String contexto) {
        return String.format("{\"prompt\": \"%s\", \"max_tokens\": 500, \"temperature\": 0.7}", contexto);
    }
}
