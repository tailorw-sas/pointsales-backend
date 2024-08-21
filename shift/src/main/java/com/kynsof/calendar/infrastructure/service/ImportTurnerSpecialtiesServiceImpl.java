package com.kynsof.calendar.infrastructure.service;

import com.kynsof.calendar.application.command.tunerSpecialties.create.CreateTurnerSpecialtiesCommand;
import com.kynsof.calendar.application.command.tunerSpecialties.importExcel.ImportTunerSpecialtiesCommand;
import com.kynsof.calendar.application.command.tunerSpecialties.importExcel.ImportTurnerSpecialtiesRequest;
import com.kynsof.calendar.application.query.tunerSpecialties.importExcel.ImportProcessStatusResponse;
import com.kynsof.calendar.domain.dto.ImportProcessStatusDto;
import com.kynsof.calendar.domain.dto.enumType.ETurnerSpecialtiesStatus;
import com.kynsof.calendar.domain.excel.ImportCache;
import com.kynsof.calendar.domain.excel.ImportProcessStatusEntity;
import com.kynsof.calendar.domain.excel.TurnerSpecialtiesRow;
import com.kynsof.calendar.domain.service.ImportTurnerSpecialtiesService;
import com.kynsof.calendar.infrastructure.excel.event.CreateTurnerSpecialtiesEvent;
import com.kynsof.calendar.infrastructure.repository.redis.ImportCacheRepository;
import com.kynsof.calendar.infrastructure.repository.redis.ImportProcessStatusRepository;
import com.kynsof.share.core.application.excel.ExcelBean;
import com.kynsof.share.core.application.excel.ReaderConfiguration;
import com.kynsof.share.core.domain.exception.ExcelException;
import com.kynsof.share.core.infrastructure.excel.ExcelBeanReader;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class ImportTurnerSpecialtiesServiceImpl implements ImportTurnerSpecialtiesService {

    private final ImportProcessStatusRepository statusRepository;
    private final ImportCacheRepository importCacheRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    public ImportTurnerSpecialtiesServiceImpl(ImportProcessStatusRepository statusRepository,
                                              ImportCacheRepository importCacheRepository,
                                              ApplicationEventPublisher applicationEventPublisher) {
        this.statusRepository = statusRepository;
        this.importCacheRepository = importCacheRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Async
    @Override
    public void readExcel(ImportTurnerSpecialtiesRequest request) {
        ReaderConfiguration readerConfiguration = new ReaderConfiguration();
        readerConfiguration.setIgnoreHeaders(true);
        InputStream inputStream = new ByteArrayInputStream(request.getFile());
        readerConfiguration.setInputStream(inputStream);
        readerConfiguration.setReadLastActiveSheet(true);
        readerConfiguration.setStartReadRow(2);
        ExcelBeanReader<TurnerSpecialtiesRow> reader = new ExcelBeanReader<>(readerConfiguration, TurnerSpecialtiesRow.class);
        ExcelBean<TurnerSpecialtiesRow> beanReader = new ExcelBean<>(reader);
        this.readAndCachingExcel(beanReader, request.getImportProcessId());
        this.readCacheAndSave(request.getImportProcessId());
    }


    private void readAndCachingExcel(ExcelBean<TurnerSpecialtiesRow> excelBean, String importProcessId) {

        for (TurnerSpecialtiesRow row : excelBean) {
            ImportCache importCache = new ImportCache(
                    null, importProcessId, row.getRowNumber(),
                    row.getAppoimentDate(),
                    row.getPatient(),
                    row.getIdentificationNumber(),
                    row.getCodDoctor(), row.getDoctor(),
                    row.getCodSpecialties(),
                    row.getSpecialties(),
                    row.getMedicalRecord()
            );
            importCacheRepository.save(importCache);

        }

    }

    private void readCacheAndSave(String importProcessId) {

        Pageable pageable = PageRequest.of(0, 500, Sort.by(Sort.Direction.ASC, "id"));
        Page<ImportCache> cacheList;
        List<CreateTurnerSpecialtiesCommand> commandList = new ArrayList<>();

        do {
            cacheList = importCacheRepository.findAllByImportProcessId(importProcessId, pageable);
            cacheList.forEach(importCache -> {
                CreateTurnerSpecialtiesCommand comman = new CreateTurnerSpecialtiesCommand(importCache.getMedicalRecord(),
                        importCache.getPatient(),
                        importCache.getIdentificationNumber(),
                        //Hay que ver como hacer el match en la bd , aqui estoy poniendo el code que viene el excel
                        importCache.getCodDoctor(),//Aqui hay que cambiar esto por el uuid del resource
                        importCache.getCodSpecialties(),//Aqui hay que cambiar esto por el uuid de la especialidad
                        ETurnerSpecialtiesStatus.PENDING.name()
                        );
                commandList.add(comman);
            });
            CreateTurnerSpecialtiesEvent createTurnerSpecialtiesEvent = new CreateTurnerSpecialtiesEvent(this,commandList);
            applicationEventPublisher.publishEvent(createTurnerSpecialtiesEvent);

            pageable = pageable.next();
        } while (cacheList.hasNext());


    }

    @Override
    public ImportProcessStatusResponse importStatus(String importProcessId) {
        ImportProcessStatusDto paymentImportStatusDto = statusRepository.findByImportProcessId(importProcessId)
                .map(ImportProcessStatusEntity::toAggregate)
                .orElseThrow();
        if (paymentImportStatusDto.isHasError()) {
            throw new ExcelException(paymentImportStatusDto.getExceptionMessage());
        }
        return new ImportProcessStatusResponse(paymentImportStatusDto.getStatus());
    }
}
