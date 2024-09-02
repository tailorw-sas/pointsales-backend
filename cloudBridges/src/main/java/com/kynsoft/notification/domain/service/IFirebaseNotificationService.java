package com.kynsoft.notification.domain.service;

import com.kynsoft.notification.application.command.sendFirebase.NotificationRequest;
import com.kynsoft.notification.application.command.sendFirebase.SubscriptionRequestDto;

public interface IFirebaseNotificationService {
    void subscribeToTopic(SubscriptionRequestDto subscriptionRequestDto);

    void unsubscribeFromTopic(SubscriptionRequestDto subscriptionRequestDto);

    String sendPnsToDevice(NotificationRequest notificationRequestDto);

    void sendMultipleNotificationsUsers(NotificationRequest notificationRequestDto);
}
