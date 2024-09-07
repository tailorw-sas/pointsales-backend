package com.kynsoft.notification.infrastructure.service;

import com.kynsof.share.core.domain.exception.BusinessNotFoundException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.exception.GlobalBusinessException;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsoft.notification.domain.dto.EmailListDto;
import com.kynsoft.notification.domain.service.EmailListService;
import com.kynsoft.notification.infrastructure.entity.EmailList;
import com.kynsoft.notification.infrastructure.repository.command.EmailListWriteRepository;
import com.kynsoft.notification.infrastructure.repository.query.EmailListReadRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
    public List<EmailListDto> getEmailListByCampaignId(String campaignId) {
        Optional<List<EmailList>> result= readRepository.findEmailListByCampaignId(UUID.fromString(campaignId));
        if (result.isPresent()){
            return result.get().stream().map(EmailList::toAggregate).toList();
        }
        throw new BusinessNotFoundException(new GlobalBusinessException(DomainErrorMessage.BUSINESS_NOT_FOUND,
                new ErrorField("id", "The email list not found.")));

    }
}
