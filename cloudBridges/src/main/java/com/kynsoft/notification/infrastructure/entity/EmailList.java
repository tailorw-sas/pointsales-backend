package com.kynsoft.notification.infrastructure.entity;

import com.kynsoft.notification.domain.dto.EmailListDto;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.generator.EventType;

import java.util.Objects;
import java.util.UUID;

@Entity(name = "emaillist")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EmailList {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    @Column(columnDefinition = "serial", name = "email_list_id")
    @Generated(event = EventType.INSERT)
    private Long emailListId;
    private String email;
    private String clientName;
    private String clientLastname;
    @ManyToOne
    private Campaign campaign;

    @Embedded
    private AuditMetadata auditMetadata;


    public EmailListDto toAggregate(){
        return EmailListDto.builder()
                .id(id)
                .emailListId(emailListId)
                .email(email)
                .clientName(clientName)
                .clientLastname(clientLastname)
                .campaign(Objects.nonNull(campaign)?campaign.toAggregate():null)
                .build();
    }
}
