package com.kynsoft.notification.infrastructure.service;

import com.kynsof.share.core.domain.exception.BusinessNotFoundException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.exception.GlobalBusinessException;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.specifications.GenericSpecificationsBuilder;
import com.kynsoft.notification.domain.dto.CampaignDto;
import com.kynsoft.notification.domain.dtoEnum.CampaignStatus;
import com.kynsoft.notification.domain.event.SendEmailEvent;
import com.kynsoft.notification.domain.service.CampaignService;
import com.kynsoft.notification.infrastructure.entity.Campaign;
import com.kynsoft.notification.infrastructure.repository.command.CampaignWriteRepository;
import com.kynsoft.notification.infrastructure.repository.query.CampaignReadRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CampaignServiceImpl implements CampaignService {

    private final CampaignReadRepository readRepository;
    private final CampaignWriteRepository writeRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    public CampaignServiceImpl(CampaignReadRepository readRepository, CampaignWriteRepository writeRepository,
                               ApplicationEventPublisher applicationEventPublisher) {
        this.readRepository = readRepository;
        this.writeRepository = writeRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public UUID createCampaign(CampaignDto campaignToCreate) {
        return writeRepository.save(campaignToCreate.toAggregate()).getId();
    }

    @Override
    public void updateCampaign(CampaignDto campaignToUpdate) {
         writeRepository.save(campaignToUpdate.toAggregate());
    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filter) {
        filterCriteria(filter);

        GenericSpecificationsBuilder<Campaign> specifications = new GenericSpecificationsBuilder<>(filter);
        Page<Campaign> data = readRepository.findAll(specifications, pageable);

        return new PaginatedResponse(data.getContent(),
                data.getTotalPages(),
                data.getNumberOfElements(),
                data.getTotalElements(),
                data.getSize(),
                data.getNumber());
    }

    @Override
    public void sendEmailCampaign(String campaignId) {
            applicationEventPublisher.publishEvent(new SendEmailEvent(campaignId));
    }

    @Override
    public CampaignDto getCampaignById(String campaignId) {
        return readRepository.findOneWithEagerRelationships(UUID.fromString(campaignId))
                .map(Campaign::toAggregate)
                .orElseThrow(()-> new BusinessNotFoundException(new GlobalBusinessException(DomainErrorMessage.BUSINESS_NOT_FOUND,
                        new ErrorField("campaignId", "The campaign not found."))));
    }

    @Override
    public PaginatedResponse getAllCampaign(Pageable pageable) {
        Page<Campaign> data= readRepository.findAllWithEagerRelationships(pageable);
        return new PaginatedResponse(data.getContent(),
                data.getTotalPages(),
                data.getNumberOfElements(),
                data.getTotalElements(),
                data.getSize(),
                data.getNumber());
    }

    private void filterCriteria(List<FilterCriteria> filterCriteria) {
        for (FilterCriteria filter : filterCriteria) {

            if ("status".equals(filter.getKey()) && filter.getValue() instanceof String) {
                try {
                    CampaignStatus enumValue = CampaignStatus.valueOf((String) filter.getValue());
                    filter.setValue(enumValue);
                } catch (IllegalArgumentException e) {
                    System.err.println("Valor inválido para el tipo Enum Status: " + filter.getValue());
                }
            }
        }
    }
}
