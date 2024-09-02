package com.kynsoft.notification.infrastructure.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.*;
import com.google.gson.Gson;
import com.kynsoft.notification.application.command.sendFirebase.NotificationRequest;
import com.kynsoft.notification.application.command.sendFirebase.SubscriptionRequestDto;
import com.kynsoft.notification.domain.service.IFirebaseNotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class FirebaseNotificationService implements IFirebaseNotificationService {

    private static final Logger logger = LoggerFactory.getLogger(FirebaseNotificationService.class);

    @Value("${app.firebase-config}")
    private String firebaseConfig;

    private FirebaseApp firebaseApp;

    @PostConstruct
    private void initialize() {
        try {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(new ClassPathResource(firebaseConfig).getInputStream()))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                this.firebaseApp = FirebaseApp.initializeApp(options);
            } else {
                this.firebaseApp = FirebaseApp.getInstance();
            }
        } catch (IOException e) {
            logger.error("Error creating FirebaseApp", e);
        }
    }

    @Override
    public void subscribeToTopic(SubscriptionRequestDto subscriptionRequestDto) {
        try {
            FirebaseMessaging.getInstance(firebaseApp)
                    .subscribeToTopic(subscriptionRequestDto.getTokens(), subscriptionRequestDto.getTopicName());
            logger.info("Successfully subscribed to topic: {}", subscriptionRequestDto.getTopicName());
        } catch (FirebaseMessagingException e) {
            logger.error("Failed to subscribe to topic: {}", subscriptionRequestDto.getTopicName(), e);
        }
    }

    @Override
    public void unsubscribeFromTopic(SubscriptionRequestDto subscriptionRequestDto) {
        try {
            FirebaseMessaging.getInstance(firebaseApp)
                    .unsubscribeFromTopic(subscriptionRequestDto.getTokens(), subscriptionRequestDto.getTopicName());
            logger.info("Successfully unsubscribed from topic: {}", subscriptionRequestDto.getTopicName());
        } catch (FirebaseMessagingException e) {
            logger.error("Failed to unsubscribe from topic: {}", subscriptionRequestDto.getTopicName(), e);
        }
    }

    @Override
    public String sendPnsToDevice(NotificationRequest notificationRequestDto) {
        if (notificationRequestDto.getTokens().isEmpty()) {
            logger.warn("No tokens provided for sending message to device.");
            return null;
        }

        Message message = Message.builder()
                .setToken(notificationRequestDto.getTokens().get(0))
                .putData("body", new Gson().toJson(notificationRequestDto))
                .putData("priority", "high")
                .putData("click_action", "FLUTTER_NOTIFICATION_CLICK")
                .putData("contentAvailable", "true")
                .build();

        try {
            String response = FirebaseMessaging.getInstance(firebaseApp).send(message);
            logger.info("Successfully sent message to device: {}", response);
            return response;
        } catch (FirebaseMessagingException e) {
            logger.error("Error sending message to device", e);
            return null;
        }
    }

    @Override
    public void sendMultipleNotificationsUsers(NotificationRequest notificationRequestDto) {
        List<String> registrationTokens = notificationRequestDto.getTokens();
        if (registrationTokens.isEmpty()) {
            logger.warn("No tokens provided for sending multiple notifications.");
            return;
        }

        AndroidConfig androidConfig = createAndroidConfig();
        ApnsConfig apnsConfig = createApnsConfig();

        // Dividir la lista de tokens en sublistas de hasta 100 elementos
        List<List<String>> tokenBatches = splitTokensIntoBatches(registrationTokens);

        // Enviar mensajes a cada sublista
        sendBatchedNotifications(notificationRequestDto, tokenBatches, androidConfig, apnsConfig);
    }

    private AndroidConfig createAndroidConfig() {
        return AndroidConfig.builder()
                .setPriority(AndroidConfig.Priority.HIGH)
                .build();
    }

    private ApnsConfig createApnsConfig() {
        return ApnsConfig.builder()
                .putHeader("apns-priority", "10")
                .setAps(Aps.builder().setContentAvailable(true).setSound("default").build())
                .build();
    }

    private List<List<String>> splitTokensIntoBatches(List<String> tokens) {
        return IntStream.range(0, (tokens.size() + 99) / 100)
                .mapToObj(i -> tokens.subList(i * 100, Math.min((i + 1) * 100, tokens.size())))
                .collect(Collectors.toList());
    }

    private Integer sendBatchedNotifications(NotificationRequest notificationRequestDto, List<List<String>> tokenBatches,
                                             AndroidConfig androidConfig, ApnsConfig apnsConfig) {
        int result = 1;

        for (List<String> subList : tokenBatches) {
            MulticastMessage message = buildMulticastMessage(notificationRequestDto, subList, androidConfig, apnsConfig);

            try {
                BatchResponse response = FirebaseMessaging.getInstance(firebaseApp).sendMulticast(message);
                logBatchResponse(response);
            } catch (FirebaseMessagingException e) {
                result = 0;
                logger.error("Error sending multicast message to batch: {}", subList, e);
            }
        }
        return result;
    }

    private MulticastMessage buildMulticastMessage(NotificationRequest notificationRequestDto, List<String> tokens,
                                                   AndroidConfig androidConfig, ApnsConfig apnsConfig) {
        return MulticastMessage.builder()
                .putData("body", new Gson().toJson(notificationRequestDto))
                .putData("priority", "high")
                .putData("click_action", "FLUTTER_NOTIFICATION_CLICK")
                .putData("contentAvailable", "true")
                .setAndroidConfig(androidConfig)
                .setApnsConfig(apnsConfig)
                .addAllTokens(tokens)
                .build();
    }

    private void logBatchResponse(BatchResponse response) {
        logger.info("Multicast message sent with {} successes and {} failures",
                response.getSuccessCount(), response.getFailureCount());
        response.getResponses().forEach(resp -> {
            if (!resp.isSuccessful()) {
                logger.error("Error sending message: {}", resp.getException().getMessage());
            }
        });
    }
}