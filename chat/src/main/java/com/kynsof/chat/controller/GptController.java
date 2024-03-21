//package com.kynsof.chat.controller;
//
//import io.github.flashvayne.chatgpt.dto.ChatRequest;
//import io.github.flashvayne.chatgpt.dto.ChatResponse;
//import io.github.flashvayne.chatgpt.service.ChatgptService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/chat")
//public class GptController {
//    @Autowired
//    private ChatgptService chatGptService;
////    public void afterPropertiesSet()
//
//    @GetMapping("chat")
//    public String chatWith(@RequestParam String message){
//        return chatGptService.sendMessage(message);
//    }
//
//    @GetMapping("prompt")
//    public ChatResponse prompt(@RequestParam String message){
//        Integer maxToxens = 300;
//        Double temperature = 0.5;
//        Double top = 1.0;
//        String model= "davinci-002";
//
//        ChatRequest chatRequest = new ChatRequest(model,message,maxToxens, temperature,top);
//        ChatResponse respose = chatGptService.sendChatRequest(chatRequest);
//        return respose;
//    }
//}
