package com.kynsof.shift.infrastructure.service;

import com.kynsof.share.core.application.excel.ExcelBean;
import com.kynsof.share.core.application.excel.ReaderConfiguration;
import com.kynsof.share.core.domain.exception.ExcelException;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.excel.ExcelBeanReader;
import com.kynsof.shift.application.command.tunerSpecialties.create.CreateTurnerSpecialtiesCommand;
import com.kynsof.shift.application.command.tunerSpecialties.importExcel.ImportTurnerSpecialtiesRequest;
import com.kynsof.shift.application.query.tunerSpecialties.importExcel.ImportProcessStatusResponse;
import com.kynsof.shift.domain.dto.ImportProcessStatusDto;
import com.kynsof.shift.domain.dto.ResourceDto;
import com.kynsof.shift.domain.dto.ServiceDto;
import com.kynsof.shift.domain.dto.enumType.EImportProcessStatus;
import com.kynsof.shift.domain.dto.enumType.ETurnerSpecialtiesStatus;
import com.kynsof.shift.domain.excel.TurnerSpecialtiesExcelRow;
import com.kynsof.shift.domain.service.IResourceService;
import com.kynsof.shift.domain.service.IServiceService;
import com.kynsof.shift.domain.service.ImportTurnerSpecialtiesService;
import com.kynsof.shift.infrastructure.entity.redis.ImportProcessStatus;
import com.kynsof.shift.infrastructure.entity.redis.TurnerSpecialtiesCache;
import com.kynsof.shift.infrastructure.entity.redis.TurnerSpecialtiesExcelRowError;
import com.kynsof.shift.infrastructure.excel.event.CreateTurnerSpecialtiesEvent;
import com.kynsof.shift.infrastructure.excel.event.ImportErrorEvent;
import com.kynsof.shift.infrastructure.excel.validator.TurnerSpecialtiesCodDoctorCellValidator;
import com.kynsof.shift.infrastructure.excel.validator.TurnerSpecialtiesExcelCellValidatorFactory;
import com.kynsof.shift.infrastructure.excel.validator.TurnerSpecialtiesServicesCellValidator;
import com.kynsof.shift.infrastructure.repository.redis.ImportProcessStatusRepository;
import com.kynsof.shift.infrastructure.repository.redis.TurnerSpecialtiesCacheRepository;
import com.kynsof.shift.infrastructure.repository.redis.TurnerSpecialtiesErrorCacheRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ImportTurnerSpecialtiesServiceImpl implements ImportTurnerSpecialtiesService {
    private final Logger logger = LoggerFactory.getLogger(ImportTurnerSpecialtiesService.class);
    private final ImportProcessStatusRepository statusRepository;
    private final TurnerSpecialtiesCacheRepository turnerSpecialtiesCacheRepository;
    private final TurnerSpecialtiesErrorCacheRepository errorCacheRepository;
    private final IResourceService resourceService;
    private final IServiceService serviceService;
    private final TurnerSpecialtiesExcelCellValidatorFactory cellValidatorFactory;
    private final ApplicationEventPublisher applicationEventPublisher;
    private TurnerSpecialtiesCodDoctorCellValidator codDoctorCellValidator;
    private TurnerSpecialtiesServicesCellValidator specialtiesServicesCellValidator;

    public ImportTurnerSpecialtiesServiceImpl(ImportProcessStatusRepository statusRepository,
                                              TurnerSpecialtiesCacheRepository turnerSpecialtiesCacheRepository,
                                              TurnerSpecialtiesErrorCacheRepository errorCacheRepository,
                                              IResourceService resourceService, IServiceService serviceService,
                                              TurnerSpecialtiesExcelCellValidatorFactory cellValidatorFactory,
                                              ApplicationEventPublisher applicationEventPublisher) {
        this.statusRepository = statusRepository;
        this.turnerSpecialtiesCacheRepository = turnerSpecialtiesCacheRepository;
        this.errorCacheRepository = errorCacheRepository;
        this.resourceService = resourceService;
        this.serviceService = serviceService;
        this.cellValidatorFactory = cellValidatorFactory;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Async
    @Override
    public void excelProcessor(ImportTurnerSpecialtiesRequest request) {
        logger.info("Iniciando proceso de importacion");
        try {
            ExcelBean<TurnerSpecialtiesExcelRow> excelBean = this.createExcelBean(request);
            this.sendInitImportProcessEvent(request.getImportProcessId());
            for (TurnerSpecialtiesExcelRow row : excelBean) {
                if (validExcelRow(row,request))
                    cachingExcelRow(row, request);
            }
            this.createTurnerSpecialtiesFromCache(request);
            this.sendEndImportProcessEvent(request.getImportProcessId());
            this.clearCache(request.getImportProcessId());
            logger.info("Finalizado el proceso de importacion");
        } catch (Exception e) {
            logger.error("Ha ocurrido un error al importar");
            e.printStackTrace();
            this.processImportError(request.getImportProcessId(), e);
            this.clearCache(request.getImportProcessId());
        }
    }

    @Override
    public ImportProcessStatusResponse turnerSpecialtiesImportStatus(String importProcessId) {
        ImportProcessStatusDto paymentImportStatusDto = statusRepository.findByImportProcessId(importProcessId)
                .map(ImportProcessStatus::toAggregate)
                .orElseThrow();
        if (paymentImportStatusDto.isHasError()) {
            throw new ExcelException(paymentImportStatusDto.getExceptionMessage());
        }
        return new ImportProcessStatusResponse(paymentImportStatusDto.getStatus());
    }

    @Override
    public PaginatedResponse getTurnerSpecialtiesImportError(String importProcessId, Pageable pageable) {
        Page<TurnerSpecialtiesExcelRowError> page = errorCacheRepository.
                findAllByImportProcessId(importProcessId, pageable);
        return new PaginatedResponse(page.getContent(),
                page.getTotalPages(), page.getNumberOfElements(),
                page.getTotalElements(),
                page.getSize(),
                page.getNumber());
    }

    private void sendInitImportProcessEvent(String importProcessId) {
        ImportProcessStatusDto importProcessStatusDto =
                new ImportProcessStatusDto(null,
                        EImportProcessStatus.RUNNING.name(),
                        importProcessId, false, "");
        statusRepository.save(importProcessStatusDto.toAggregate());
    }

    private void sendEndImportProcessEvent(String importProcessId) {
        ImportProcessStatusDto importProcessStatusDto = statusRepository.findByImportProcessId(importProcessId)
                .map(ImportProcessStatus::toAggregate).orElseThrow();
        importProcessStatusDto.setStatus(EImportProcessStatus.FINISHED.name());
        statusRepository.save(importProcessStatusDto.toAggregate());
    }

    private void clearCache(String importProcessId){
        Pageable pageable = PageRequest.of(0, 500, Sort.by(Sort.Direction.ASC, "id"));
        Page<TurnerSpecialtiesCache> cacheList;
        do {
            cacheList = turnerSpecialtiesCacheRepository.findAllByImportProcessId(importProcessId, pageable);
            turnerSpecialtiesCacheRepository.deleteAll(cacheList.getContent());
            pageable = pageable.next();
        } while (cacheList.hasNext());
    }
    private ExcelBean<TurnerSpecialtiesExcelRow> createExcelBean(ImportTurnerSpecialtiesRequest request) {
        ReaderConfiguration readerConfiguration = this.buildExcelReaderConfiguration(request);
        ExcelBeanReader<TurnerSpecialtiesExcelRow> reader = new ExcelBeanReader<>(readerConfiguration, TurnerSpecialtiesExcelRow.class);
        return new ExcelBean<>(reader);
    }

    private ReaderConfiguration buildExcelReaderConfiguration(ImportTurnerSpecialtiesRequest request) {
        return new ReaderConfiguration
                .ReaderConfigurationBuilder()
                .inputStream(new ByteArrayInputStream(request.getFile()))
                .setIgnoreHeaders(true)
                .setReadLastActiveSheet(true)
                .setStartReadRow(2)
                .build();
    }

    private void cachingExcelRow(TurnerSpecialtiesExcelRow row, ImportTurnerSpecialtiesRequest importRequest) {
        TurnerSpecialtiesCache turnerSpecialtiesCache = new TurnerSpecialtiesCache(
                null,
                importRequest.getImportProcessId(),
                row.getRowNumber(),
                row.getAppoimentDate(),
                row.getMedicalRecord(),
                row.getPatient(),
                row.getIdentificationNumber(),
                row.getCodDoctor(), row.getDoctor(),
                row.getCodSpecialties(),
                row.getSpecialties()
        );
        turnerSpecialtiesCacheRepository.save(turnerSpecialtiesCache);
    }

    private void createTurnerSpecialtiesFromCache(ImportTurnerSpecialtiesRequest request) {
        Pageable pageable = PageRequest.of(0, 500, Sort.by(Sort.Direction.ASC, "id"));
        Page<TurnerSpecialtiesCache> cacheList;
        List<CreateTurnerSpecialtiesCommand> commandList = new ArrayList<>();

        do {
            cacheList = turnerSpecialtiesCacheRepository.findTurnerSpecialtiesCacheByImportProcessId(request.getImportProcessId(), pageable);
            cacheList.forEach(turnerSpecialtiesCache -> {
                CreateTurnerSpecialtiesCommand comman = new CreateTurnerSpecialtiesCommand(turnerSpecialtiesCache.getMedicalRecord(),
                        turnerSpecialtiesCache.getPatient(),
                        turnerSpecialtiesCache.getIdentificationNumber(),
                        this.getResourceFromCode(turnerSpecialtiesCache.getCodDoctor()).getId().toString(),
                        this.getServiceFromCode(turnerSpecialtiesCache.getCodSpecialties()).getId().toString(),
                        ETurnerSpecialtiesStatus.PENDING.name(),
                        LocalDateTime.ofInstant(turnerSpecialtiesCache.getAppoimentDate().toInstant(), ZoneId.systemDefault()),
                        LocalTime.now(),
                        request.getBussinessId()
                );
                commandList.add(comman);
            });
            CreateTurnerSpecialtiesEvent createTurnerSpecialtiesEvent = new CreateTurnerSpecialtiesEvent(this, commandList);
            applicationEventPublisher.publishEvent(createTurnerSpecialtiesEvent);
            pageable = pageable.next();
        } while (cacheList.hasNext());


    }

    private ResourceDto getResourceFromCode(String code) {
        return resourceService.findByCode(code);
    }

    private ServiceDto getServiceFromCode(String code) {
        return serviceService.findByCode(code);
    }

    private boolean validExcelRow(TurnerSpecialtiesExcelRow row,ImportTurnerSpecialtiesRequest request) {
        if (Objects.isNull(specialtiesServicesCellValidator))
            specialtiesServicesCellValidator = (TurnerSpecialtiesServicesCellValidator)
                    cellValidatorFactory.makeExcelRuleValidator(TurnerSpecialtiesServicesCellValidator.VALIDATOR_ID);
        if (Objects.isNull(codDoctorCellValidator))
            codDoctorCellValidator = (TurnerSpecialtiesCodDoctorCellValidator)
                    cellValidatorFactory.makeExcelRuleValidator(TurnerSpecialtiesCodDoctorCellValidator.VALIDATOR_ID);
        List<ErrorField> errorContainer = new ArrayList<>();
        errorContainer.addAll(specialtiesServicesCellValidator.validate(row));
        errorContainer.addAll(codDoctorCellValidator.validate(row));
        if (!errorContainer.isEmpty()) {
            ImportErrorEvent event = new ImportErrorEvent(this,
                    new TurnerSpecialtiesExcelRowError(null, row.getRowNumber(),
                            request.getImportProcessId(), errorContainer, row));
            applicationEventPublisher.publishEvent(event);
        }

        return errorContainer.isEmpty();
    }

    private void processImportError(String importProcessId, Exception e) {
        ImportProcessStatusDto statusDto = statusRepository.findByImportProcessId(importProcessId)
                .map(ImportProcessStatus::toAggregate)
                .orElseThrow();
        statusDto.setStatus(EImportProcessStatus.FINISHED.name());
        statusDto.setHasError(true);
        statusDto.setExceptionMessage(e.getMessage());
        statusRepository.save(statusDto.toAggregate());
    }


}
