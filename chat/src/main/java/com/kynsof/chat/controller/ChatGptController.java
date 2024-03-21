package com.kynsof.chat.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import reactor.core.publisher.Mono;


@RestController
@SessionAttributes("contexto")
public class ChatGptController {
    private final ChatGptService chatGptService;

    @Autowired
    public ChatGptController(ChatGptService chatGptService) {
        this.chatGptService = chatGptService;
    }

    @GetMapping("/iniciar")
    public String iniciarConversacion(@RequestParam String promptInicial, HttpSession session) {
        session.setAttribute("contexto", promptInicial);
        return "Conversación iniciada. Usa /chat para continuar.";
    }

    @GetMapping("/chat")
    public Mono<String> chat(@RequestParam String entrada, HttpSession session) {
        String contexto = (String) session.getAttribute("contexto");
        contexto = contexto != null ? contexto + " " + entrada : entrada;
        session.setAttribute("contexto", contexto);
        return chatGptService.obtenerRespuesta(contexto);
    }

    @GetMapping("/finalizar")
    public String finalizarConversacion(HttpSession session, SessionStatus status) {
        status.setComplete(); // Marca la sesión como completa para limpiar los atributos gestionados
        session.invalidate(); // Invalida la sesión completa para eliminar todos los atributos
        return "Conversación finalizada.";
    }
}