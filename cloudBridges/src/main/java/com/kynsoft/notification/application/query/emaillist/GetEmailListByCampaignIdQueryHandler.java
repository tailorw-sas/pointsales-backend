package com.kynsoft.notification.application.query.emaillist;

import com.kynsof.share.core.domain.bus.query.IQueryHandler;
import com.kynsoft.notification.domain.service.EmailListService;
import org.springframework.stereotype.Component;

@Component
public class GetEmailListByCampaignIdQueryHandler implements IQueryHandler<GetEmailListByCampaignIdQuery,GetEmailListByCampaignIdResponse> {

    private final EmailListService emailListService;

    public GetEmailListByCampaignIdQueryHandler(EmailListService emailListService) {
        this.emailListService = emailListService;
    }
    @Override
    public GetEmailListByCampaignIdResponse handle(GetEmailListByCampaignIdQuery query) {
        return new GetEmailListByCampaignIdResponse( emailListService.getEmailListByCampaignId(query.getCampaignId(), ));
    }
}
