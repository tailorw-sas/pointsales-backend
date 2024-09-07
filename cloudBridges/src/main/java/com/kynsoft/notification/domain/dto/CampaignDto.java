package com.kynsoft.notification.domain.dto;

import com.kynsoft.notification.domain.dtoEnum.CampaignStatus;
import com.kynsoft.notification.infrastructure.entity.Campaign;
import com.kynsoft.notification.infrastructure.entity.EmailList;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * DTO for {@link com.kynsoft.notification.infrastructure.entity.Campaign}
 */
@Value
@Builder
public class CampaignDto implements Serializable {
    @NotNull
    UUID id;
    UUID ownerId;
    Long campaignId;
    String code;
    LocalDate campaignDate;
    CampaignStatus status;
    long amountEmailSent;
    long amountEmailOpen;
    TemplateDto template;
    TenantDto tenant;
    Set<EmailListDto> emailList;

    public Campaign toAggregate(){
        return Campaign.builder()
                .id(id)
                .campaignId(campaignId)
                .code(code)
                .campaignDate(campaignDate)
                .status(status)
                .amountEmailSent(amountEmailSent)
                .amountEmailOpen(amountEmailOpen)
                .template(Objects.nonNull(template)?template.toAggregate():null)
                .tenant(Objects.nonNull(tenant)?tenant.toAggregate():null)
                .emailList(Objects.nonNull(emailList)?emailList.stream().map(EmailListDto::toAggregate).collect(Collectors.toSet()):null)
                .ownerId(ownerId)
                .build();

    }

}