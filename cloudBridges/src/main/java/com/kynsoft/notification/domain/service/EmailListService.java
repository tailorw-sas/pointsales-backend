package com.kynsoft.notification.domain.service;

import com.kynsoft.notification.domain.dto.EmailListDto;

import java.util.List;
import java.util.UUID;

public interface EmailListService {

    UUID createEmailList(EmailListDto emailListDto);
    void createBulkEmailList(List<EmailListDto> emailListDtoList);

    List<EmailListDto> getEmailListByCampaignId(String campaignId);
}
