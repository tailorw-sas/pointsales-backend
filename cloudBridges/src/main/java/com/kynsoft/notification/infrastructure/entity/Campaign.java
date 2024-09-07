package com.kynsoft.notification.infrastructure.entity;

import com.kynsoft.notification.domain.CampaignStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.generator.EventType;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity(name = "campaign")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Campaign {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(columnDefinition = "serial", name = "campaign_gen_id")
    @Generated(event = EventType.INSERT)
    private Long campaignId;

    private String code;

    private LocalDate campaignDate;

    private CampaignStatus status;

    private long amountEmailSent;

    private long amountEmailOpen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_id")
    private TemplateEntity template;

    @ManyToOne()
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "email_list_id")
    private Set<EmailList> emailList;


}
