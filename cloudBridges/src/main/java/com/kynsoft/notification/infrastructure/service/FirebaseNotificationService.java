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
            logger.error("Error subscribing to topic: {}", e.getMessage(), e);
        }
    }

    @Override
    public void unsubscribeFromTopic(SubscriptionRequestDto subscriptionRequestDto) {
        try {
            FirebaseMessaging.getInstance(firebaseApp)
                    .unsubscribeFromTopic(subscriptionRequestDto.getTokens(), subscriptionRequestDto.getTopicName());
            logger.info("Successfully unsubscribed from topic: {}", subscriptionRequestDto.getTopicName());
        } catch (FirebaseMessagingException e) {
            logger.error("Error unsubscribing from topic: {}", e.getMessage(), e);
        }
    }

    @Override
    public String sendPnsToDevice(NotificationRequest notificationRequestDto) {
        if (notificationRequestDto.getTokens().isEmpty()) {
            logger.warn("No tokens provided for sending message to device.");
            return null;
        }

        Message message = buildMessage(notificationRequestDto, notificationRequestDto.getTokens().get(0));

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

        // Dividir tokens en lotes de hasta 100
        List<List<String>> tokenBatches = IntStream.range(0, (registrationTokens.size() + 99) / 100)
                .mapToObj(i -> registrationTokens.subList(i * 100, Math.min((i + 1) * 100, registrationTokens.size())))
                .toList();

        AndroidConfig androidConfig = createAndroidConfig();
        ApnsConfig apnsConfig = createApnsConfig();

        for (List<String> subList : tokenBatches) {
            if (!subList.isEmpty()) {
                MulticastMessage message = MulticastMessage.builder()
                        .putData("body", new Gson().toJson(notificationRequestDto))
                        .putData("priority", "high")
                        .putData("click_action", "FLUTTER_NOTIFICATION_CLICK")
                        .putData("contentAvailable", "true")
                        .setAndroidConfig(androidConfig)
                        .setApnsConfig(apnsConfig)
                        .addAllTokens(subList)
                        .build();
                try {
                    BatchResponse response = FirebaseMessaging.getInstance(firebaseApp).sendEachForMulticast(message);
                    logBatchResponse(response);
                } catch (FirebaseMessagingException e) {
                    logger.error("Error sending multicast message", e);
                }
            }
        }
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

    private Message buildMessage(NotificationRequest notificationRequestDto, String token) {
        return Message.builder()
                .setToken(token)
                .putData("body", new Gson().toJson(notificationRequestDto))
                .putData("priority", "high")
                .putData("click_action", "FLUTTER_NOTIFICATION_CLICK")
                .putData("contentAvailable", "true")
                .build();
    }

    private void logBatchResponse(BatchResponse response) {
        logger.info("Batch message sent with {} successes and {} failures",
                response.getSuccessCount(), response.getFailureCount());
        response.getResponses().forEach(resp -> {
            if (!resp.isSuccessful()) {
                logger.error("Error sending message: {}", resp.getException().getMessage());
            }
        });
    }
}