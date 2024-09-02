package com.kynsoft.notification.application.command.sendFirebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FCMNotificationService {

    @Value("${app.firebase-config}")
    private String firebaseConfig;

    private FirebaseApp firebaseApp;

    // ChoferDao choferDao = new ChoferDao();

    @PostConstruct
    private void initialize() {
        try {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(
                            GoogleCredentials.fromStream(new ClassPathResource(firebaseConfig).getInputStream()))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                this.firebaseApp = FirebaseApp.initializeApp(options);
            } else {
                this.firebaseApp = FirebaseApp.getInstance();
            }
        } catch (IOException e) {
            System.out.println("Create FirebaseApp Error");
        }
    }

    public void subscribeToTopic(SubscriptionRequestDto subscriptionRequestDto) {
        try {
            FirebaseMessaging.getInstance(firebaseApp).subscribeToTopic(subscriptionRequestDto.getTokens(),
                    subscriptionRequestDto.getTopicName());
        } catch (FirebaseMessagingException e) {
            System.out.println("Firebase subscribe to topic fail");
        }
    }

    public void unsubscribeFromTopic(SubscriptionRequestDto subscriptionRequestDto) {
        try {
            FirebaseMessaging.getInstance(firebaseApp).unsubscribeFromTopic(subscriptionRequestDto.getTokens(),
                    subscriptionRequestDto.getTopicName());
        } catch (FirebaseMessagingException e) {
            System.out.println("Firebase unsubscribe from topic fail");
        }
    }

    public String sendPnsToDevice(NotificationRequestDto notificationRequestDto) {
        Message message = Message.builder()
                .setToken(notificationRequestDto.getToken())
                // .setNotification(new Notification(notificationRequestDto.getTitle(),
                // notificationRequestDto.getBody()))
                // .putData("content", notificationRequestDto.getTituloNotificacion())
                .putData("body", new Gson().toJson(notificationRequestDto))
                .putData("priority", "high")
                .putData("click_action", "FLUTTER_NOTIFICATION_CLICK")
                .putData("contentAvailable", "true")
                .build();

        String response = null;
        try {
            response = FirebaseMessaging.getInstance().send(message);
//            addNotification(
//                    new PushNotification(notificationRequestDto.getTitle(),
//                            notificationRequestDto.getBody(), new Date()));
        } catch (FirebaseMessagingException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
        }

        return response;
    }

    /**
     * Metodo para enviar notificacion de un nuevo o reenviado viaje a todos los
     * usuarios.
     *
     * @param notificationRequestDto datos de la notificacion.
     * 
     * @return <b>1<\b> si se envio correctamente.
     *         <p>
     *         0<\p> si ocurrio algun error.
     */
    public Integer sendMultipleNotificationsUsers(
            NotificationRequestDto notificationRequestDto/* , String operation */) {
        List<String> registrationTokens =new ArrayList<>();
        registrationTokens.add(notificationRequestDto.getToken());
        int resultado = 1;
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
        return resultado;
    }

//    public List<PushNotification> listLatestNotifications() {
//        return this.pushNotificationRepository.listLatestNotifications();
//    }
//
//    public PushNotification addNotification(PushNotification notification) {
//        return this.pushNotificationRepository.save(notification);
//    }
//
//    public void initialCharge() {
//        List<ScheduledNotification> notifications = new ArrayList<>();
//        notifications.add(new ScheduledNotification("¿Cómo te sientes hoy?", "No olvides registrar tus emociones hoy",
//                ENotificationFrequency.DAILY));
//        notifications.add(new ScheduledNotification("Registrar tus emociones es importante",
//                "Recuerda que el cuadro de seguimiento de emociones te podría servir en una próxima consulta",
//                ENotificationFrequency.DAILY));
//        notifications.add(new ScheduledNotification("¡Es hora de mantenerte al dia!",
//                "Hoy tenemos nuevos contenidos ¡puedes revisarlos!", ENotificationFrequency.ON_NEW_CONTENT));
//        notifications.add(new ScheduledNotification("¡Recuerda!",
//                "Si conoces alguien que esté pasando por depresión dile que descargue nuestra app gratuita",
//                ENotificationFrequency.MONTHLY));
//
//        notifications.forEach(notification -> {
//            boolean existNotification = this.scheduledNotificationRepository.existsByTitle(notification.getTitle());
//            if (!existNotification)
//                this.scheduledNotificationRepository.save(notification);
//        });
//    }
//
//    public List<ScheduledNotification> listDailyNotifications() {
//        return this.scheduledNotificationRepository.listDailyNotifications();
//    }
//
//    public List<ScheduledNotification> listMonthlyNotification() {
//        return this.scheduledNotificationRepository.listMonthlyNotifications();
//    }
//
//    public void deleteOldNotifications() {
//        this.pushNotificationRepository.deleteOldNotifications();
//    }

}