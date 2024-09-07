package com.kynsoft.notification.application.query.emaillist;

import com.kynsof.share.core.domain.bus.query.IResponse;
import com.kynsoft.notification.domain.dto.EmailListDto;
import lombok.Getter;

import java.util.List;

@Getter
public class GetEmailListByCampaignIdResponse implements IResponse {
    private final List<EmailListDto> emailListDtoList;
    public GetEmailListByCampaignIdResponse(List<EmailListDto> emailListDtoList) {
        this.emailListDtoList = emailListDtoList;
    }
}
