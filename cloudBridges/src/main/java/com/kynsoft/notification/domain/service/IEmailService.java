package com.kynsoft.notification.domain.service;

import com.kynsoft.notification.domain.dto.EmailRequest;
import com.mailjet.client.errors.MailjetException;
import org.springframework.web.multipart.MultipartFile;

public interface IEmailService {
    public boolean sendMail(String toEmail, String subject, String message);
    public boolean sendMailHtml(String toEmail, String subject, String message);
    public boolean sendMessageWithAttachment(String toEmail, String subject, String text, MultipartFile file);
    public boolean sendMessageWithAttachmentArray(String toEmail, String subject, String text, MultipartFile [] file);
    boolean sendEmailMailjet(EmailRequest emailRequest) throws  MailjetException;
}

