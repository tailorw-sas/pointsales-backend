package com.kynsoft.notification.infrastructure.service;

import com.kynsof.share.core.application.excel.ExcelBean;
import com.kynsof.share.core.application.excel.ReaderConfiguration;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.excel.ExcelBeanReader;
import com.kynsoft.notification.application.command.emaillist.importEmailList.ImportEmailListRequest;
import com.kynsoft.notification.application.query.emaillist.importError.ImportEmailListErrorRequest;
import com.kynsoft.notification.application.query.emaillist.processStatus.ImportEmailListProcessStatusRequest;
import com.kynsoft.notification.application.query.emaillist.processStatus.ImportEmailListProcessStatusResponse;
import com.kynsoft.notification.domain.bean.ImportEmailListRow;
import com.kynsoft.notification.domain.dtoEnum.ProcessStatus;
import com.kynsoft.notification.domain.event.CreateEmailListEvent;
import com.kynsoft.notification.domain.event.CreateImportStatusEvent;
import com.kynsoft.notification.domain.service.ImportEmailListService;
import com.kynsoft.notification.infrastructure.entity.redis.ImportEmailListErrorRow;
import com.kynsoft.notification.infrastructure.entity.redis.ImportEmailListProcessStatus;
import com.kynsoft.notification.infrastructure.repository.redis.ImportEmailListErrorRedisRepository;
import com.kynsoft.notification.infrastructure.repository.redis.ImportEmailListProcessStatusRedisRepository;
import io.jsonwebtoken.lang.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImportEmailListServiceImpl implements ImportEmailListService {
    private final Logger log = LoggerFactory.getLogger(ImportEmailListServiceImpl.class);
    @Value("${import.bach.size}")
    private int batchSize;
    @Value("${import.limit}")
    private int importLimit;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final ImportEmailListErrorRedisRepository errorRedisRepository;

    private final ImportEmailListProcessStatusRedisRepository statusRedisRepository;

    public ImportEmailListServiceImpl(ApplicationEventPublisher applicationEventPublisher,
                                      ImportEmailListErrorRedisRepository errorRedisRepository,
                                      ImportEmailListProcessStatusRedisRepository statusRedisRepository) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.errorRedisRepository = errorRedisRepository;
        this.statusRedisRepository = statusRedisRepository;
    }

    @Override
    @Async
    public void importEmailList(ImportEmailListRequest request) {
        Assert.notNull(request.getEmailList(), "The contact list can't be null");
        Assert.notNull(request.getImportProcessId(), "The import process id can't be null");
        DataBufferUtils.join(request.getEmailList().content())
                .flatMap(dataBuffer -> {
                    byte[] bytes = new byte[dataBuffer.readableByteCount()];
                    dataBuffer.read(bytes);
                    DataBufferUtils.release(dataBuffer);
                    return Mono.just(bytes);
                }).subscribe(content->this.readExcel(request,content));
    }

    private void readExcel(ImportEmailListRequest request,byte[] content) {
        ReaderConfiguration readerConfiguration = new ReaderConfiguration.ReaderConfigurationBuilder()
                .inputStream(new ByteArrayInputStream(content))
                .setIgnoreHeaders(true)
                .setReadLastActiveSheet(true)
                .build();
        ExcelBeanReader<ImportEmailListRow> beanReader = new ExcelBeanReader<>(readerConfiguration, ImportEmailListRow.class);
        ExcelBean<ImportEmailListRow> excelBean = new ExcelBean<>(beanReader);
        try{
            this.createStartProcessStatus(request.getImportProcessId());
            this.readExcelContent(excelBean);
            this.createEndProcessStatus(request.getImportProcessId());
        }catch (Exception e){
            this.processImportError(e.getMessage(), request.getImportProcessId());
        }
    }

    private void readExcelContent(ExcelBean<ImportEmailListRow> excelBean){
        int batchControl = 0;
        int importTotal = 0;
        List<ImportEmailListRow> emailListRowList = new ArrayList<>();
        for (ImportEmailListRow emailListRow : excelBean) {
            if (importTotal == importLimit) {
                break;
            }
            emailListRowList.add(emailListRow);
            batchControl++;
            if (batchControl == batchSize) {
                this.createEmailListBulk(emailListRowList);
                batchControl = 0;
            }
            importTotal++;
        }
    }

    private void createEmailListBulk(List<ImportEmailListRow> rows) {
        CreateEmailListEvent event = new CreateEmailListEvent(rows);
        applicationEventPublisher.publishEvent(event);
    }
    private void createStartProcessStatus(String importProcessId){
        applicationEventPublisher.publishEvent(ImportEmailListProcessStatus
                .builder()
                .status(ProcessStatus.RUNNING)
                .importProcessId(importProcessId)
                .build()
        );
    }

    private void createEndProcessStatus(String importProcessId){
        applicationEventPublisher.publishEvent(ImportEmailListProcessStatus
                .builder()
                .status(ProcessStatus.FINISHED)
                .importProcessId(importProcessId)
                .build()
        );
    }
    private void processImportError(String message,String importProcessId){
        log.error(message);
        CreateImportStatusEvent statusEvent = new CreateImportStatusEvent(ImportEmailListProcessStatus
                .builder()
                .status(ProcessStatus.FINISHED)
                .hasError(true)
                .importProcessId(importProcessId)
                .exceptionMessage(message)
                .build()
        );
        applicationEventPublisher.publishEvent(statusEvent);
    }

    @Override
    public PaginatedResponse getImportEmailListErrors(ImportEmailListErrorRequest request) {
        Page<ImportEmailListErrorRow> importErrorPages = errorRedisRepository.
                findAllByImportProcessId(request.getQuery(),
                        request.getPageable());
        return new PaginatedResponse(importErrorPages.getContent(),
                importErrorPages.getTotalPages(),
                importErrorPages.getNumberOfElements(),
                importErrorPages.getTotalElements(),
                importErrorPages.getSize(),
                importErrorPages.getNumber());
    }

    @Override
    public ImportEmailListProcessStatusResponse getImportEmailListProcessStatus(ImportEmailListProcessStatusRequest request) {
        Optional<ImportEmailListProcessStatus> entity =
                statusRedisRepository.findByImportProcessId(request.getImportProcessId());

        if (entity.isPresent()) {
            if (entity.get().isHasError()) {
                throw new RuntimeException(entity.get().getExceptionMessage());
            }
            return new ImportEmailListProcessStatusResponse(entity.get().getStatus().name());
        }
        return null;
    }
}
