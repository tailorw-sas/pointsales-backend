package com.kynsoft.notification.infrastructure.entity;

import com.kynsoft.notification.domain.dto.TemplateDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TemplateEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(nullable = false, unique = true)
    private String templateCode;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true, length = 1024)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mailjet_config_id")
    private MailjetConfiguration mailjetConfig;

    public TemplateEntity(TemplateDto dto) {
        this.id = dto.getId();
        this.templateCode = dto.getTemplateCode();
        this.name = dto.getName();
        this.description = dto.getDescription();
    }


    public TemplateDto toAggregate() {
        return new TemplateDto(this.id, this.templateCode, this.name, this.description, mailjetConfig.toAggregate());
    }
}