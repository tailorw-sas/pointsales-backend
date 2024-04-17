package com.kynsof.calendar.infrastructure.service;

import com.kynsof.calendar.application.query.QualificationResponse;
import com.kynsof.calendar.domain.dto.QualificationDto;
import com.kynsof.calendar.domain.dto.enumType.EQualificationStatus;
import com.kynsof.calendar.domain.rules.QualificationDescriptionMustBeNotNullRule;
import com.kynsof.calendar.domain.rules.QualificationDescriptionMustBeUniqueRule;
import com.kynsof.calendar.domain.service.IQualificationService;
import com.kynsof.calendar.infrastructure.entity.Qualification;
import com.kynsof.calendar.infrastructure.entity.specifications.QualificationDeleteSpecifications;
import com.kynsof.calendar.infrastructure.entity.specifications.QualificationSpecifications;
import com.kynsof.calendar.infrastructure.repository.command.QualificationWriteDataJPARepository;
import com.kynsof.calendar.infrastructure.repository.query.QualificationReadDataJPARepository;
import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.exception.BusinessException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.kafka.config.SimpleEmailKafka;
import com.kynsof.share.core.domain.kafka.producer.email.ProducerEmailEventService;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.redis.CacheConfig;
import com.kynsof.share.core.infrastructure.specifications.GenericSpecificationsBuilder;
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
public class QualificationServiceImpl implements IQualificationService {

    @Autowired
    private QualificationWriteDataJPARepository repositoryCommand;

    @Autowired
    private QualificationReadDataJPARepository repositoryQuery;

    @Autowired
    private ProducerEmailEventService producerEmailEventService;

    @Override
    public void create(QualificationDto qualification) {
        RulesChecker.checkRule(new QualificationDescriptionMustBeNotNullRule(this, qualification));
        RulesChecker.checkRule(new QualificationDescriptionMustBeUniqueRule(this, qualification));

        qualification.setStatus(EQualificationStatus.ACTIVE);

        try {
            this.repositoryCommand.save(new Qualification(qualification));
            this.producerEmailEventService.create(new SimpleEmailKafka("penaescalonayannier@gmail.com", "Hola", "Nuevo mensaje...."));
        } catch (Exception e) {
            throw new BusinessException(DomainErrorMessage.COLUMN_UNIQUE, "Qualification not insert, the descriptions is already exists: " + e.getCause().getCause().getLocalizedMessage().split("Key")[1]);
        }

    }

    @Override
    public void delete(UUID id) {
        QualificationDto objectDelete = this.findById(id);
        objectDelete.setStatus(EQualificationStatus.INACTIVE);

        objectDelete.setDeleted(true);

        objectDelete.setDescription(objectDelete.getDescription() + UUID.randomUUID().toString());

        this.repositoryCommand.save(new Qualification(objectDelete));
    }

    @Cacheable(cacheNames = CacheConfig.QUALIFICATION_CACHE, unless = "#result == null")
    @Override
    public QualificationDto findById(UUID id) {

        //Optional<Qualification> object = this.repositoryQuery.findById(id);
        Optional<Qualification> object = this.repositoryQuery.findOne(new QualificationDeleteSpecifications(id));
        if (object.isPresent()) {
            return object.get().toAggregate();
        }

        throw new BusinessException(DomainErrorMessage.QUALIFICATION_NOT_FOUND, "Qualification not found.");
    }

    @Override
    public PaginatedResponse findAll(Pageable pageable, UUID idQualification, String filter) {
        QualificationSpecifications specifications = new QualificationSpecifications(idQualification, filter);
        Page<Qualification> data = this.repositoryQuery.findAll(specifications, pageable);

        List<QualificationResponse> qualification = new ArrayList<>();
        for (Qualification q : data.getContent()) {
            qualification.add(new QualificationResponse(q.toAggregate()));
        }
        return new PaginatedResponse(qualification, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        GenericSpecificationsBuilder<Qualification> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<Qualification> data = this.repositoryQuery.findAll(specifications, pageable);
        return getPaginatedResponse(data);
    }

    private PaginatedResponse getPaginatedResponse(Page<Qualification> data) {
        List<QualificationResponse> patients = new ArrayList<>();
        for (Qualification q : data.getContent()) {
            patients.add(new QualificationResponse(q.toAggregate()));
        }
        return new PaginatedResponse(patients, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }

    @Override
    public void update(QualificationDto qualification) {
        this.repositoryCommand.save(new Qualification(qualification));
    }

    @Override
    public Long countByDescription(String description) {
        return repositoryQuery.countByDescription(description);
    }

}
