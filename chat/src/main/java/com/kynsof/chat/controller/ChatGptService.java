//package com.kynsof.chat.controller;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;
//
//@Service
//public class ChatGptService {
//    private final WebClient webClient;
//
//    public ChatGptService(@Value("${openai.api.key}") String apiKey) {
//        this.webClient = WebClient.builder()
//                .baseUrl("https://api.openai.com/v1")
//                .defaultHeader("Authorization", "Bearer " + apiKey)
//                .build();
//    }
//
//    public Mono<String> obtenerRespuesta(String contextoCompleto) {
//        return this.webClient.post()
//                .uri("/completions") // Cambiado de /engines/davinci/completions a /completions
//                .header("Content-Type", "application/json")
//                .bodyValue(crearPayload(contextoCompleto))
//                .retrieve()
//                .bodyToMono(String.class);
//    }
//
//    private String crearPayload(String contexto) {
//        // Asegúrate de incluir el modelo en el cuerpo de la solicitud, como "model": "text-davinci-003"
//        return String.format("{\"model\": \"gpt-4-0125-preview\", \"prompt\": \"%s\", \"max_tokens\": 500, \"temperature\": 0.7}", contexto);
//    }
//}