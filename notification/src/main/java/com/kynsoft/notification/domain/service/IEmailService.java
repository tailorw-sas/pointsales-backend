package com.kynsoft.notification.domain.service;

import com.kynsoft.notification.application.dto.EmailRequest;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import jakarta.mail.MessagingException;
import org.springframework.web.multipart.MultipartFile;

public interface IEmailService {
    public boolean sendMail(String toEmail, String subject, String message);
    public boolean sendMailHtml(String toEmail, String subject, String message);
    public boolean sendMessageWithAttachment(String toEmail, String subject, String text, MultipartFile file) throws MessagingException;
    public boolean sendMessageWithAttachmentArray(String toEmail, String subject, String text, MultipartFile [] file) throws MessagingException;
    boolean sendMailTemplateK(EmailRequest emailRequest) throws MailjetSocketTimeoutException, MailjetException;
}

