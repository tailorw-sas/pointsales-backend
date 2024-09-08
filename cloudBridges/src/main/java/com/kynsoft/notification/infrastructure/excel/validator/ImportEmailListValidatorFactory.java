package com.kynsoft.notification.infrastructure.excel.validator;

import com.kynsoft.notification.domain.bean.ImportEmailListRow;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class ImportEmailListValidatorFactory extends ValidatorFactory<ImportEmailListRow> {

    private ImportEmailListEmailValidator importEmailListEmailValidator;
    protected ImportEmailListValidatorFactory(ApplicationEventPublisher applicationEventPublisher) {
        super(applicationEventPublisher);
    }

    @Override
    public void createValidators() {
        importEmailListEmailValidator = new ImportEmailListEmailValidator();
    }

    @Override
    public boolean validate(ImportEmailListRow toValidate) {
        importEmailListEmailValidator.validate(toValidate,errorFieldList);
        this.sendErrorEvent(toValidate);
        boolean result = errorFieldList.isEmpty();
        this.clearErrors();
        return result;
    }
}
