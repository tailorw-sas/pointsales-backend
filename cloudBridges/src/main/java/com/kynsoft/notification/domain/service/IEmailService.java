package com.kynsoft.notification.domain.service;

import com.kynsoft.notification.domain.dto.EmailRequest;
import com.mailjet.client.errors.MailjetException;

import java.util.List;

public interface IEmailService {

    void sendEmailMailjet(EmailRequest emailRequest, String mailjetApiKey, String mailjetApiSecret,
                          String fromEmail, String fromName) throws  MailjetException;

    Long createContactList(String name, List<String> emails, String mailjetApiKey, String mailjetApiSecret) throws MailjetException;
    void addContactsToList(Long listId, List<String> emails, String mailjetApiKey, String mailjetApiSecret) throws MailjetException;
    void removeContactFromList(Long listId, String email, String mailjetApiKey, String mailjetApiSecret) throws MailjetException;
    void deleteContactList(Long listId, String mailjetApiKey, String mailjetApiSecret) throws MailjetException;

    String createCampaign(String campaignName, String senderEmail, String senderName, String subject, Long templateId,
                          Long contactListId, String mailjetApiKey, String mailjetApiSecret) throws MailjetException;

    boolean sendCampaign(String campaignId, String mailjetApiKey, String mailjetApiSecret) throws MailjetException;
}

