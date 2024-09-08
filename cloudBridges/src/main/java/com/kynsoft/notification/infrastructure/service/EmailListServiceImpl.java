package com.kynsoft.notification.infrastructure.service;

import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.specifications.GenericSpecificationsBuilder;
import com.kynsoft.notification.domain.dto.EmailListDto;
import com.kynsoft.notification.domain.service.EmailListService;
import com.kynsoft.notification.infrastructure.entity.EmailList;
import com.kynsoft.notification.infrastructure.repository.command.EmailListWriteRepository;
import com.kynsoft.notification.infrastructure.repository.query.EmailListReadRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmailListServiceImpl implements EmailListService {
    private final EmailListWriteRepository writeRepository;
    private final EmailListReadRepository readRepository;

    public EmailListServiceImpl(EmailListWriteRepository writeRepository, EmailListReadRepository readRepository) {
        this.writeRepository = writeRepository;
        this.readRepository = readRepository;
    }

    @Override
    public UUID createEmailList(EmailListDto emailListDto) {
        return writeRepository.save(emailListDto.toAggregate()).getId();
    }

    @Override
    public void createBulkEmailList(List<EmailListDto> emailListDtoList) {
        writeRepository.saveAll(emailListDtoList.stream().map(EmailListDto::toAggregate).toList());
    }

    @Override
    public Page<EmailListDto> getEmailListByCampaignId(String campaignId, Pageable pageable) {
        return readRepository.findEmailListByCampaignId(UUID.fromString(campaignId), pageable).map(EmailList::toAggregate);

    }

    @Override
    public PaginatedResponse search(Pageable page, List<FilterCriteria> filter) {
        GenericSpecificationsBuilder<EmailList> specifications = new GenericSpecificationsBuilder<>(filter);
        Page<EmailList> data = readRepository.findAll(specifications, page);
        return new PaginatedResponse(data.getContent().stream().map(EmailList::toAggregate).toList(),
                data.getTotalPages(),
                data.getNumberOfElements(),
                data.getTotalElements(),
                data.getSize(),
                data.getNumber());
    }

}
