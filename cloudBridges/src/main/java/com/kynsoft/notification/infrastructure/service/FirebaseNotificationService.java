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
         //   handleFirebaseMessagingException(e);
        }
    }

    @Override
    public void unsubscribeFromTopic(SubscriptionRequestDto subscriptionRequestDto) {
        try {
            FirebaseMessaging.getInstance(firebaseApp)
                    .unsubscribeFromTopic(subscriptionRequestDto.getTokens(), subscriptionRequestDto.getTopicName());
            logger.info("Successfully unsubscribed from topic: {}", subscriptionRequestDto.getTopicName());
        } catch (FirebaseMessagingException e) {
           // handleFirebaseMessagingException(e);
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
            //handleFirebaseMessagingException(e);
            return null;
        }
    }

    @Override
    public void sendMultipleNotificationsUsers(
            NotificationRequest notificationRequestDto/* , String operation */) {
        List<String> registrationTokens = notificationRequestDto.getTokens();
        Integer resultado = 1;
        // Se fracciona la lista de tokens de 100 en 100 ya que el metodo addAllTokens
        // permite esta cantidad como maximo
        for (int i = 0; i < registrationTokens.size(); i += 100) {
            List<String> subList = registrationTokens.subList(i, Math.min(registrationTokens.size(), i + 100));
            BatchResponse response = null;
            AndroidConfig androidConfig = AndroidConfig.builder().setPriority(AndroidConfig.Priority.HIGH).build();
            ApnsConfig apnsConfig = ApnsConfig.builder()
                    .putHeader("apns-priority", "10")
                    .setAps(Aps.builder().setContentAvailable(true)
                            .setSound("default")
                            .build())
                    .build();

            if (subList != null && subList.size() > 0) {
                MulticastMessage message = MulticastMessage.builder()
                        .putData("body", new Gson().toJson(notificationRequestDto))
                        .putData("priority", "high")
                        .putData("click_action", "FLUTTER_NOTIFICATION_CLICK")
                        .putData("contentAvailable", "true")
                        // .setNotification(new Notification(notificationRequestDto.getTitle(),
                        // notificationRequestDto.getBody()))
                        // .putData("body", new Gson().toJson(tripRequest))
                        //// .putData("priority", "high")
                        //// .putData("contentAvailable", "true")
                        // .putData("operation", operation)
                        .setAndroidConfig(androidConfig)
                        .setApnsConfig(apnsConfig)
                        .addAllTokens(subList)
                        .build();
                try {
                    response = FirebaseMessaging.getInstance().sendMulticast(message);
                } catch (FirebaseMessagingException e) {
                    resultado = 0;
                    System.out.println(e.getMessage());
                    System.out.println(e.getCause());
                }
            }
        }

    }

//    @Override
//    public void sendMultipleNotificationsUsers(NotificationRequest notificationRequestDto) {
//        List<String> registrationTokens = notificationRequestDto.getTokens();
//        if (registrationTokens.isEmpty()) {
//            logger.warn("No tokens provided for sending multiple notifications.");
//            return;
//        }
//
//        // Crear mensajes para cada token
//        List<Message> messages = registrationTokens.stream()
//                .map(token -> buildMessage(notificationRequestDto, token))
//                .collect(Collectors.toList());
//
//        try {
//            // Enviar todos los mensajes usando sendAll
//            BatchResponse response = FirebaseMessaging.getInstance(firebaseApp).sendAll(messages);
//            logBatchResponse(response);
//        } catch (FirebaseMessagingException e) {
//            logger.error("Error sending notifications", e);
//        }
//    }

    // Construcción del mensaje individual
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

//    private void handleFirebaseMessagingException(FirebaseMessagingException e) {
//        if (e.getErrorCode() == null) {
//            logger.error("Unknown error occurred with no specific ErrorCode: {}", e.getMessage(), e);
//            return;
//        }
//        switch (e.getErrorCode()) {
//            case INVALID_ARGUMENT:
//                logger.error("Invalid argument: {}", e.getMessage(), e);
//                break;
//            case FAILED_PRECONDITION:
//                logger.error("Failed precondition: {}", e.getMessage(), e);
//                break;
//            case OUT_OF_RANGE:
//                logger.error("Out of range: {}", e.getMessage(), e);
//                break;
//            case UNAUTHENTICATED:
//                logger.error("Unauthenticated: {}", e.getMessage(), e);
//                break;
//            case PERMISSION_DENIED:
//                logger.error("Permission denied: {}", e.getMessage(), e);
//                break;
//            case NOT_FOUND:
//                logger.error("Not found: {}", e.getMessage(), e);
//                break;
//            case CONFLICT:
//                logger.error("Conflict: {}", e.getMessage(), e);
//                break;
//            case ABORTED:
//                logger.error("Aborted: {}", e.getMessage(), e);
//                break;
//            case ALREADY_EXISTS:
//                logger.error("Already exists: {}", e.getMessage(), e);
//                break;
//            case RESOURCE_EXHAUSTED:
//                logger.error("Resource exhausted: {}", e.getMessage(), e);
//                break;
//            case CANCELLED:
//                logger.error("Cancelled: {}", e.getMessage(), e);
//                break;
//            case DATA_LOSS:
//                logger.error("Data loss: {}", e.getMessage(), e);
//                break;
//            case UNKNOWN:
//                logger.error("Unknown error: {}", e.getMessage(), e);
//                break;
//            case INTERNAL:
//                logger.error("Internal error: {}", e.getMessage(), e);
//                break;
//            case UNAVAILABLE:
//                logger.error("Service unavailable: {}", e.getMessage(), e);
//                break;
//            case DEADLINE_EXCEEDED:
//                logger.error("Deadline exceeded: {}", e.getMessage(), e);
//                break;
//            default:
//                logger.error("Unhandled FirebaseMessagingException occurred: {}", e.getMessage(), e);
//        }
   // }
}