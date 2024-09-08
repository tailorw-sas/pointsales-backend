package com.kynsoft.notification.application.query.emaillist;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsoft.notification.domain.dto.EmailListDto;
import com.kynsoft.notification.domain.service.EmailListService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class GetEmailListByCampaignIdQueryHandler implements IQueryHandler<GetEmailListByCampaignIdQuery,GetEmailListByCampaignIdResponse> {

    private final EmailListService emailListService;

    public GetEmailListByCampaignIdQueryHandler(EmailListService emailListService) {
        this.emailListService = emailListService;
    }
    @Override
    public GetEmailListByCampaignIdResponse handle(GetEmailListByCampaignIdQuery query) {
        Page<EmailListDto> response = emailListService.getEmailListByCampaignId(query.getCampaignId(),query.getPageable());
        return new GetEmailListByCampaignIdResponse( response.stream().toList());
    }
}
