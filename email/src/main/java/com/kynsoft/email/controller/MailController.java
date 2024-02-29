package com.kynsoft.email.controller;

import com.kynsoft.email.shared.application.ApiResponse2xx;
import com.kynsoft.email.application.SendEmailRequest;
import com.kynsoft.email.application.SendEmailResponse;
import com.kynsoft.email.infrastructure.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/mail")
public class MailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send/simple")
    public ResponseEntity<ApiResponse2xx<SendEmailResponse>> sendEmailSimple(@RequestBody SendEmailRequest email) {
        boolean result = emailService.sendMail(email.getToEmail(), email.getSubject(), email.getMessage());
        String msg = "";
        if (result) {
            msg = "Message sent successfully!";
        } else {
            msg = "Message sent failed!";
        }
        return ResponseEntity.ok(new ApiResponse2xx<SendEmailResponse>(new SendEmailResponse(result, msg), HttpStatus.OK));
    }

    @PostMapping("/send/template")
    public ResponseEntity<ApiResponse2xx<SendEmailResponse>> sendEmailTemplate(@RequestBody SendEmailRequest email) {
        boolean result = emailService.sendMailHtml(email.getToEmail(), email.getSubject(), email.getMessage());
        String msg = "";
        if (result) {
            msg = "Message sent successfully!";
        } else {
            msg = "Message sent failed!";
        }
        return ResponseEntity.ok(new ApiResponse2xx<SendEmailResponse>(new SendEmailResponse(result, msg), HttpStatus.OK));
    }

    @PostMapping("/send/attachment")
    public ResponseEntity<ApiResponse2xx<SendEmailResponse>> sendEmailTemplateAttachment(
            @RequestParam(defaultValue = "") String email,
            @RequestParam(defaultValue = "") String subject,
            @RequestParam(defaultValue = "") String menssage,
            @RequestParam("file") MultipartFile file) {

        String msg = "";
        boolean result = true;
        try {
            result = emailService.sendMessageWithAttachment(email, subject, menssage, file);
            
            if (result) {
                msg = "Message sent successfully!";
            } else {
                msg = "Message sent failed!";
            }
            return ResponseEntity.ok(new ApiResponse2xx<SendEmailResponse>(new SendEmailResponse(result, msg), HttpStatus.OK));
        } catch (MessagingException ex) {
            //throw new BusinessException(ApiErrorStatus.MIDDLEWARE_MAIL_FAILED, "Please check the email provided.");
            return ResponseEntity.ok(new ApiResponse2xx<SendEmailResponse>(new SendEmailResponse(result, msg), HttpStatus.OK));
        }
        
    }

    @PostMapping("/send/multi/attachment")
    public ResponseEntity<ApiResponse2xx<SendEmailResponse>> sendEmailTemplateAttachmentArray(
            @RequestParam(defaultValue = "") String email,
            @RequestParam(defaultValue = "") String subject,
            @RequestParam(defaultValue = "") String menssage,
            @RequestParam("files") MultipartFile [] files) {
        
        String msg = "";
        boolean result = true;
        try {
            result = emailService.sendMessageWithAttachmentArray(email, subject, menssage, files);
            
            if (result) {
                msg = "Message sent successfully!";
            } else {
                msg = "Message sent failed!";
            }
            return ResponseEntity.ok(new ApiResponse2xx<SendEmailResponse>(new SendEmailResponse(result, msg), HttpStatus.OK));
        } catch (MessagingException ex) {
            //throw new BusinessException(ApiErrorStatus.MIDDLEWARE_MAIL_FAILED, "Please check the email provided.");
            return ResponseEntity.ok(new ApiResponse2xx<SendEmailResponse>(new SendEmailResponse(result, msg), HttpStatus.OK));
        }
        
    }

}
