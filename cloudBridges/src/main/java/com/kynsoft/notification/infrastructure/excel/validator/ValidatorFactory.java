package com.kynsoft.notification.infrastructure.excel.validator;

import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsoft.notification.domain.bean.ImportEmailListRow;
import com.kynsoft.notification.domain.event.CreateImportErrorEvent;
import com.kynsoft.notification.infrastructure.entity.redis.ImportEmailListErrorRow;
import org.springframework.context.ApplicationEventPublisher;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class ValidatorFactory<T> {

    private final ApplicationEventPublisher applicationEventPublisher;
    protected List<ErrorField> errorFieldList;
    protected Map<String, ExcelRuleValidator<ImportEmailListErrorRow>> validators;

    protected ValidatorFactory(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.errorFieldList = new ArrayList<>();
        this.validators =  new LinkedHashMap<>();
    }

    abstract public void createValidators();

    abstract public boolean validate(T toValidate);

    protected void sendErrorEvent(ImportEmailListRow emailListErrorRow){
        if (!errorFieldList.isEmpty()) {
            ImportEmailListErrorRow rowError = new ImportEmailListErrorRow(null, emailListErrorRow.getRowNumber(),
                    emailListErrorRow.getImportProcessId(), errorFieldList, emailListErrorRow);
            CreateImportErrorEvent excelRowErrorEvent = new CreateImportErrorEvent(rowError);
            applicationEventPublisher.publishEvent(excelRowErrorEvent);
        }
    }

    protected void clearErrors(){
        this.errorFieldList.clear();
    }
    public  void removeValidators(){
        if (!validators.isEmpty()){
            validators.clear();
        }
    }
}
