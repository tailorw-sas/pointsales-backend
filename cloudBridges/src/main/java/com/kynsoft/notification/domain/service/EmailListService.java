package com.kynsoft.notification.domain.service;

import com.kynsoft.notification.domain.dto.EmailListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface EmailListService {

    UUID createEmailList(EmailListDto emailListDto);
    void createBulkEmailList(List<EmailListDto> emailListDtoList);

    Page<EmailListDto> getEmailListByCampaignId(String campaignId, Pageable pageable);
}
