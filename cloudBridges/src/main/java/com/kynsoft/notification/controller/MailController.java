package com.kynsoft.notification.controller;

import com.kynsoft.notification.application.SendEmailRequest;
import com.kynsoft.notification.application.SendEmailResponse;
import com.kynsoft.notification.application.dto.EmailRequest;
import com.kynsoft.notification.application.dto.MailJetAttachment;
import com.kynsoft.notification.application.dto.MailJetRecipient;
import com.kynsoft.notification.application.dto.MailJetVar;
import com.kynsoft.notification.domain.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/api/mail")
public class MailController {

    @Autowired
    private final IEmailService emailService;

    public MailController(IEmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send/simple")
    public ResponseEntity<?>sendEmailSimple(@RequestBody SendEmailRequest email) {
        boolean result = emailService.sendMail(email.getToEmail(), email.getSubject(), email.getMessage());
        String msg = "";
        if (result) {
            msg = "Message sent successfully!";
        } else {
            msg = "Message sent failed!";
        }
        return ResponseEntity.ok(new SendEmailResponse(result, msg));
    }

    @PostMapping("/send/template")
    public ResponseEntity<?> sendEmailTemplate(@RequestBody SendEmailRequest email) {
        boolean result = emailService.sendMailHtml(email.getToEmail(), email.getSubject(), email.getMessage());
        String msg = "";
        if (result) {
            msg = "Message sent successfully!";
        } else {
            msg = "Message sent failed!";
        }
        return ResponseEntity.ok(new SendEmailResponse(result, msg));
    }

    @PostMapping("/send/attachment")
    public ResponseEntity<?> sendEmailTemplateAttachment(
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
            return ResponseEntity.ok(new SendEmailResponse(result, msg));
        } catch (Exception ex) {
            //throw new BusinessException(ApiErrorStatus.MIDDLEWARE_MAIL_FAILED, "Please check the email provided.");
            return ResponseEntity.ok(new SendEmailResponse(result, msg));
        }

    }

    @PostMapping("/send/multi/attachment")
    public ResponseEntity<?> sendEmailTemplateAttachmentArray(
            @RequestParam(defaultValue = "") String email,
            @RequestParam(defaultValue = "") String subject,
            @RequestParam(defaultValue = "") String menssage,
            @RequestParam("files") MultipartFile[] files) {

        String msg = "";
        boolean result = true;
        try {
            result = emailService.sendMessageWithAttachmentArray(email, subject, menssage, files);

            if (result) {
                msg = "Message sent successfully!";
            } else {
                msg = "Message sent failed!";
            }
            return ResponseEntity.ok(new SendEmailResponse(result, msg));
        } catch (Exception ex) {
            //throw new BusinessException(ApiErrorStatus.MIDDLEWARE_MAIL_FAILED, "Please check the email provided.");
            return ResponseEntity.ok(new SendEmailResponse(result, msg));
        }

    }
    @PostMapping("/send/multi/keime")
    public ResponseEntity<?> keimer(){
        boolean result = true;
        String msg = "";
//        if (fileParam.isEmpty()) {
//            return ResponseEntity.ok(new SendEmailResponse(result, msg)));
//        }
        try {
            List<MailJetAttachment> mailJetAttachments = new ArrayList<>();
            //String encodedString = convertFileToBase64(fileParam);
           // mailJetAttachments.add(new MailJetAttachment("application/pdf", "example.pdf", encodedString));
            mailJetAttachments.add(new MailJetAttachment("image/png", "image.png", "iVBORw0KGgoAAAANSUhEUgAAABQAAAALCAYAAAB/Ca1DAAAACXBIWXMAAA7EAAAOxAGVKw4bAAAAB3RJTUUH4wIIChcxurq5eQAAAAd0RVh0QXV0aG9yAKmuzEgAAAAMdEVYdERlc2NyaXB0aW9uABMJISMAAAAKdEVYdENvcHlyaWdodACsD8w6AAAADnRFWHRDcmVhdGlvbiB0aW1lADX3DwkAAAAJdEVYdFNvZnR3YXJlAF1w/zoAAAALdEVYdERpc2NsYWltZXIAt8C0jwAAAAh0RVh0V2FybmluZwDAG+aHAAAAB3RFWHRTb3VyY2UA9f+D6wAAAAh0RVh0Q29tbWVudAD2zJa/AAAABnRFWHRUaXRsZQCo7tInAAABV0lEQVQokaXSPWtTYRTA8d9N7k1zm6a+RG2x+FItgpu66uDQxbFurrr5OQQHR9FZnARB3PwSFqooddAStCBoqmLtS9omx+ESUXuDon94tnP+5+1JYm057GyQjZFP+l+S6G2FzlNe3WHtHc2TNI8zOlUUGLxsD1kDyR+EEQE2P/L8Jm/uk6RUc6oZaYM0JxtnpEX9AGPTtM6w7yzVEb61EaSNn4QD3j5m4QabH6hkVFLSUeqHyCeot0ib6BdNVGscPM/hWWr7S4Tw9TUvbpFUitHTnF6XrS+sL7O6VBSausT0FZonSkb+nZUFFm+z8Z5up5Btr1Lby7E5Zq4yPrMrLR263ZV52g+LvfW3iy6PXubUNVrnhqYNF3bmiZ1i1MmLnL7OxIWh4T+IMpYeRNyrRzyZjWg/ioh+aVgZu4WfXxaixbsRve5fiwb8epTo8+kZjSPFf/sHvgNC0/mbjJbxPAAAAABJRU5ErkJggg=="));
            mailJetAttachments.add(new MailJetAttachment("text/plain", "test.txt", "VGhpcyBpcyB5b3VyIGF0dGFjaGVkIGZpbGUhISEK"));

            List<MailJetRecipient> mailJetRecipients = new ArrayList<>();
            mailJetRecipients.add(new MailJetRecipient("gg@kynsoft.com","Keimer"));

            List<MailJetVar> vars = Arrays.asList(
                    new MailJetVar("username", "NombreUsuario"),
                    new MailJetVar("otp_token", "Token1234")
            );

          int  templateId =3931552;
            EmailRequest emailRequest = new EmailRequest(mailJetRecipients, vars, mailJetAttachments,"SubjectTest", templateId);
            result = emailService.sendEmailMailjet(emailRequest);

            if (result) {
                msg = "Message sent successfully!";
            } else {
                 msg = "Message sent failed!";
            }
            return ResponseEntity.ok(new SendEmailResponse(result, msg));
        } catch (Exception ex) {
            return ResponseEntity.ok(new SendEmailResponse(result, msg));
        }
    }
    public String convertFileToBase64(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        String encodedString = Base64.getEncoder().encodeToString(bytes);
        return encodedString;
    }

}
