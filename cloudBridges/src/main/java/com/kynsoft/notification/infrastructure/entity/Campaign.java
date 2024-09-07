package com.kynsoft.notification.infrastructure.entity;

import com.kynsoft.notification.domain.dto.CampaignDto;
import com.kynsoft.notification.domain.dtoEnum.CampaignStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
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
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity(name = "campaign")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Campaign {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(columnDefinition = "serial", name = "campaign_gen_id")
    @Generated(event = EventType.INSERT)
    private Long campaignId;

    private String code;

    private UUID ownerId;

    private LocalDate campaignDate;

    private CampaignStatus status;

    private long amountEmailSent;

    private long amountEmailOpen;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "template_id")
    private TemplateEntity template;

    @ManyToOne(fetch =FetchType.EAGER)
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "campaign",cascade = CascadeType.ALL)
    private Set<EmailList> emailList;

    @CreatedDate
    private Instant createDate;
    @LastModifiedDate
    private Instant updateDate;


    public CampaignDto toAggregate(){
        return CampaignDto.builder()
                .id(id)
                .campaignId(campaignId)
                .code(code)
                .campaignDate(campaignDate)
                .status(status)
                .amountEmailSent(amountEmailSent)
                .amountEmailOpen(amountEmailOpen)
                .template(template.toAggregate())
                .tenant(tenant.toAggregate())
                .emailList(emailList.stream().map(EmailList::toAggregate).collect(Collectors.toSet()))
                .build();

    }


}
