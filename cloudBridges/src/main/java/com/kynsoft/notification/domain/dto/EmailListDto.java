package com.kynsoft.notification.domain.dto;

import com.kynsoft.notification.infrastructure.entity.EmailList;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * DTO for {@link com.kynsoft.notification.infrastructure.entity.EmailList}
 */
@Value
@Builder
public class EmailListDto implements Serializable {
    UUID id;
    Long emailListId;
    String email;
    String clientName;
    String clientLastname;
    CampaignDto campaign;

    public EmailList toAggregate(){
        return EmailList.builder()
                .id(id)
                .emailListId(emailListId)
                .email(email)
                .clientName(clientName)
                .clientLastname(clientLastname)
                .campaign(Objects.nonNull(campaign)?campaign.toAggregate():null)
                .build();
    }

}