package com.kynsoft.notification.infrastructure.entity;


import com.kynsoft.notification.domain.dto.MailjetConfigurationDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MailjetConfiguration implements Serializable {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String mailjetApiKey;

    @Column(nullable = false)
    private String mailjetApiSecret;

    @Column(nullable = false)
    private String fromEmail;

    @Column(nullable = false)
    private String fromName;

    @OneToMany(mappedBy = "mailjetConfig", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<TemplateEntity> templates;

    public MailjetConfigurationDto toAggregate() {
        return new MailjetConfigurationDto(id, mailjetApiKey, mailjetApiSecret, fromEmail, fromName);
    }

    public MailjetConfiguration(MailjetConfigurationDto dto) {
        this.mailjetApiKey = dto.getMailjetApiKey();
        this.mailjetApiSecret = dto.getMailjetApiSecret();
        this.fromEmail = dto.getFromEmail();
        this.fromName = dto.getFromName();
    }

}