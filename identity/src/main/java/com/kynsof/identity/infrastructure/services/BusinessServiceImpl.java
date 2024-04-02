package com.kynsof.identity.infrastructure.services;

import com.kynsof.identity.application.query.business.search.BusinessResponse;
import com.kynsof.identity.domain.dto.BusinessDto;
import com.kynsof.identity.domain.dto.enumType.EBusinessStatus;
import com.kynsof.identity.domain.interfaces.service.IBusinessService;
import com.kynsof.identity.infrastructure.identity.Business;
import com.kynsof.identity.infrastructure.identity.GeographicLocation;
import com.kynsof.identity.infrastructure.repository.command.BusinessWriteDataJPARepository;
import com.kynsof.identity.infrastructure.services.kafka.producer.ProducerCreateBusinessEventService;
import com.kynsof.identity.infrastructure.repository.query.BusinessReadDataJPARepository;
import com.kynsof.identity.infrastructure.services.kafka.producer.ProducerUpdateBusinessEventService;
import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.exception.BusinessException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.kafka.entity.FileKafka;
import com.kynsof.share.core.domain.kafka.producer.s3.ProducerDeleteFileEventService;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.domain.rules.ValidateObjectNotNullRule;
import com.kynsof.share.core.infrastructure.redis.CacheConfig;
import com.kynsof.share.core.infrastructure.specifications.GenericSpecificationsBuilder;
import com.kynsof.share.utils.ConfigureTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BusinessServiceImpl implements IBusinessService {

    @Autowired
    private BusinessWriteDataJPARepository repositoryCommand;

    @Autowired
    private BusinessReadDataJPARepository repositoryQuery;

    @Autowired
    private ProducerCreateBusinessEventService createBusinessEventService;

    @Autowired
    private ProducerUpdateBusinessEventService updateBusinessEventService;

    @Autowired
    private ProducerDeleteFileEventService deleteFileEventService;

    @Override
    public void create(BusinessDto object) {
        RulesChecker.checkRule(new ValidateObjectNotNullRule(object, "Business", "Business DTO cannot be null."));
        RulesChecker.checkRule(new ValidateObjectNotNullRule(object.getId(), "Business.id", "Business ID cannot be null."));

        object.setStatus(EBusinessStatus.ACTIVE);
        object.setCreateAt(ConfigureTimeZone.getTimeZone());

        this.repositoryCommand.save(new Business(object));
        createBusinessEventService.create(object);
    }

    @Override
    public void update(BusinessDto objectDto) {
        RulesChecker.checkRule(new ValidateObjectNotNullRule(objectDto, "Business", "Business DTO cannot be null."));
        RulesChecker.checkRule(new ValidateObjectNotNullRule(objectDto.getId(), "Business.id", "Business ID cannot be null."));

        Business object = this.repositoryQuery.findById(objectDto.getId())
                .orElseThrow(() -> new BusinessException(DomainErrorMessage.QUALIFICATION_NOT_FOUND, "Qualification not found."));

        object.setDescription(objectDto.getDescription() != null ? objectDto.getDescription() : object.getDescription());
        object.setStatus(objectDto.getStatus() != null ? objectDto.getStatus() : object.getStatus());
        object.setLogo(objectDto.getLogo() != null ? objectDto.getLogo() : object.getLogo());
        object.setName(objectDto.getName() != null ? objectDto.getName() : object.getName());
        object.setLongitude(objectDto.getLongitude()!= null ? objectDto.getLongitude(): object.getLongitude());
        object.setLatitude(objectDto.getLatitude()!= null ? objectDto.getLatitude(): object.getLatitude());
        object.setRuc(objectDto.getRuc() != null ? objectDto.getRuc() : object.getRuc());
        object.setAddress(objectDto.getAddress()!= null ? objectDto.getAddress(): object.getAddress());
        object.setGeographicLocation(objectDto.getGeographicLocationDto() != null ? new GeographicLocation(objectDto.getGeographicLocationDto()) : object.getGeographicLocation());

        this.repositoryCommand.save(object);
        objectDto.setLogo(object.getLogo());
        this.updateBusinessEventService.update(objectDto);
        this.deleteFileEventService.delete(new FileKafka(object.getId(), "identity", "", null));
    }

    @Override
    public void delete(UUID id) {

        BusinessDto objectDelete = this.findById(id);
        objectDelete.setStatus(EBusinessStatus.INACTIVE);

        objectDelete.setDeleteAt(ConfigureTimeZone.getTimeZone());
        objectDelete.setDeleted(true);

        this.repositoryCommand.save(new Business(objectDelete));
    }

    @Cacheable(cacheNames = CacheConfig.BUSINESS_CACHE, unless = "#result == null")
    @Override
    public BusinessDto findById(UUID id) {

        Optional<Business> object = this.repositoryQuery.findById(id);
        if (object.isPresent()) {
            return object.get().toAggregate();
        }

        throw new BusinessException(DomainErrorMessage.BUSINESS_NOT_FOUND, "Business not found.");

    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        filterCreteria(filterCriteria);
        GenericSpecificationsBuilder<Business> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<Business> data = this.repositoryQuery.findAll(specifications, pageable);
        return getPaginatedResponse(data);
    }

    private void filterCreteria(List<FilterCriteria> filterCriteria) {
        for (FilterCriteria filter : filterCriteria) {
            if ("status".equals(filter.getKey()) && filter.getValue() instanceof String) {
                try {
                    EBusinessStatus enumValue = EBusinessStatus.valueOf((String) filter.getValue());
                    filter.setValue(enumValue);
                } catch (IllegalArgumentException e) {
                    System.err.println("Valor inválido para el tipo Enum Empresa: " + filter.getValue());
                }
            }
        }
    }

    private PaginatedResponse getPaginatedResponse(Page<Business> data) {
        List<BusinessResponse> patients = new ArrayList<>();
        for (Business o : data.getContent()) {
            patients.add(new BusinessResponse(o.toAggregate()));
        }
        return new PaginatedResponse(patients, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }

}
