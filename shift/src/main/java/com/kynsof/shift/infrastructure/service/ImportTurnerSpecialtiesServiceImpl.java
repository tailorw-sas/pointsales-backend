package com.kynsof.shift.infrastructure.service;

import com.kynsof.share.core.application.excel.ExcelBean;
import com.kynsof.share.core.application.excel.ReaderConfiguration;
import com.kynsof.share.core.domain.exception.ExcelException;
import com.kynsof.share.core.infrastructure.excel.ExcelBeanReader;
import com.kynsof.shift.application.command.tunerSpecialties.create.CreateTurnerSpecialtiesCommand;
import com.kynsof.shift.application.command.tunerSpecialties.importExcel.ImportTurnerSpecialtiesRequest;
import com.kynsof.shift.application.query.tunerSpecialties.importExcel.ImportProcessStatusResponse;
import com.kynsof.shift.domain.dto.ImportProcessStatusDto;
import com.kynsof.shift.domain.dto.ResourceDto;
import com.kynsof.shift.domain.dto.ServiceDto;
import com.kynsof.shift.domain.dto.enumType.ETurnerSpecialtiesStatus;
import com.kynsof.shift.domain.excel.TurnerSpecialtiesExcelRow;
import com.kynsof.shift.domain.service.IResourceService;
import com.kynsof.shift.domain.service.IServiceService;
import com.kynsof.shift.domain.service.ImportTurnerSpecialtiesService;
import com.kynsof.shift.infrastructure.entity.redis.ImportProcessStatus;
import com.kynsof.shift.infrastructure.entity.redis.TurnerSpecialtiesCache;
import com.kynsof.shift.infrastructure.excel.event.CreateTurnerSpecialtiesEvent;
import com.kynsof.shift.infrastructure.repository.redis.ImportProcessStatusRepository;
import com.kynsof.shift.infrastructure.repository.redis.TurnerSpecialtiesCacheRepository;
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
import java.util.ArrayList;
import java.util.List;

@Service
public class ImportTurnerSpecialtiesServiceImpl implements ImportTurnerSpecialtiesService {
    private final ImportProcessStatusRepository statusRepository;
    private final TurnerSpecialtiesCacheRepository turnerSpecialtiesCacheRepository;
    private final IResourceService resourceService;
    private final IServiceService serviceService;
    private final ApplicationEventPublisher applicationEventPublisher;

    public ImportTurnerSpecialtiesServiceImpl(ImportProcessStatusRepository statusRepository,
                                              TurnerSpecialtiesCacheRepository turnerSpecialtiesCacheRepository,
                                              IResourceService resourceService, IServiceService serviceService,
                                              ApplicationEventPublisher applicationEventPublisher) {
        this.statusRepository = statusRepository;
        this.turnerSpecialtiesCacheRepository = turnerSpecialtiesCacheRepository;
        this.resourceService = resourceService;
        this.serviceService = serviceService;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Async
    @Override
    public void excelProcessor(ImportTurnerSpecialtiesRequest request) {
        try {
            ExcelBean<TurnerSpecialtiesExcelRow> excelBean = this.createExcelBean(request);
            for (TurnerSpecialtiesExcelRow row : excelBean) {
                cachingExcelRow(row,request);
            }
            this.createCommandFromCache(request);
        }catch (Exception e){
           this.processImportError(e);
        }
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

    private void cachingExcelRow(TurnerSpecialtiesExcelRow row,ImportTurnerSpecialtiesRequest importRequest) {
        TurnerSpecialtiesCache turnerSpecialtiesCache = new TurnerSpecialtiesCache(
                null,
                importRequest.getImportProcessId(),
                row.getRowNumber(),
                row.getAppoimentDate(),
                row.getPatient(),
                row.getIdentificationNumber(),
                row.getCodDoctor(), row.getDoctor(),
                row.getCodSpecialties(),
                row.getSpecialties(),
                row.getMedicalRecord()
        );
        turnerSpecialtiesCacheRepository.save(turnerSpecialtiesCache);
    }

    private void createCommandFromCache(ImportTurnerSpecialtiesRequest request) {
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
                        LocalDateTime.now(),
                        LocalTime.ofNanoOfDay(turnerSpecialtiesCache.getAppoimentDate().getTime()),
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
    private void processImportError(Exception e){

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
}
