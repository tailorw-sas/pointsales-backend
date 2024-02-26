package com.kynsoft.email.infrastructure.service;

import com.kynsoft.email.domain.service.IEmailService;
import freemarker.template.Configuration;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EmailService implements IEmailService {

    @Value("${spring.mail.username}")
    private String from;

    private JavaMailSender javaMailSender;

    @Qualifier("getFreeMarkerConfiguration")
    @Autowired
    Configuration fmConfiguration;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public boolean sendMail(String toEmail, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(toEmail);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        mailMessage.setFrom(from);
        try {
            javaMailSender.send(mailMessage);
            return true;
        } catch (Exception e) {
            return false;
            //throw new BusinessException(ApiErrorStatus.MIDDLEWARE_MAIL_FAILED, "Please check the email provided.");
        }
    }

    @Override
    public boolean sendMailHtml(String toEmail, String subject, String message) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(toEmail);
            Map<String, Object> model = new HashMap<>();
            model.put("userName", toEmail);
            model.put("verificationCode", message);
            String body = getContentFromTemplate(model);
            mimeMessageHelper.setText(message, true);

            javaMailSender.send(mimeMessageHelper.getMimeMessage());
            return true;
        } catch (MessagingException e) {
            return false;
            //throw new BusinessException(ApiErrorStatus.MIDDLEWARE_MAIL_FAILED, "Please check the email provided.");
        }
    }

    public String getContentFromTemplate(Map<String, Object> model) {
        StringBuffer content = new StringBuffer();

        try {
            String contentHtml = FreeMarkerTemplateUtils.processTemplateIntoString(fmConfiguration.getTemplate("emailES.ftlh"), model);
            content.append(contentHtml);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    @Override
    public boolean sendMessageWithAttachment(String toEmail, String subject, String text, MultipartFile file) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(text);
        if (file != null) {
            helper.addAttachment(file.getOriginalFilename(), file);
        }
        try {
            javaMailSender.send(message);
            return true;
        } catch (Exception e) {
            return false;
        }
        
    }

    @Override
    public boolean sendMessageWithAttachmentArray(String toEmail, String subject, String text, MultipartFile [] file) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(text, true);
        for (MultipartFile f : file) {
            if (f != null) {
                helper.addAttachment(f.getOriginalFilename(), f);
            }
        }
        try {
            javaMailSender.send(message);
            return true;
        } catch (Exception e) {
            return false;
        }
        
    }
}
