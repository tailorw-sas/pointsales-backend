package com.kynsof.shift.infrastructure.excel.validator;

import com.kynsof.share.core.application.excel.validator.ExcelRuleValidator;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.shift.domain.excel.TurnerSpecialtiesExcelRow;
import com.kynsof.shift.domain.service.IServiceService;
import org.springframework.context.ApplicationEventPublisher;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TurnerSpecialtiesServicesCellValidator implements ExcelRuleValidator<TurnerSpecialtiesExcelRow> {
    public static final String VALIDATOR_ID="COD_SERVICE_VALIDATOR";

    private final IServiceService serviceService;

    public TurnerSpecialtiesServicesCellValidator(IServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @Override
    public List<ErrorField> validate(TurnerSpecialtiesExcelRow obj) {
        List<ErrorField> errorFieldList = new ArrayList<>();
        this.validateIfFieldIsEmpty(obj, errorFieldList);
        this.validateIfFieldValueExist(obj, errorFieldList);
        return errorFieldList;
    }

    private void validateIfFieldIsEmpty(TurnerSpecialtiesExcelRow obj, List<ErrorField> errorFieldList) {
        if (Objects.isNull(obj.getCodDoctor()) || obj.getCodDoctor().isEmpty()) {
            errorFieldList.add(new ErrorField("cod doctor", "El  campo coddoctor no puede ser vacio"));
        }
    }

    private void validateIfFieldValueExist(TurnerSpecialtiesExcelRow row, List<ErrorField> errorFieldList) {
        if (Objects.nonNull(row.getCodDoctor()) && serviceService.existServiceByCode(row.getCodSpecialties())) {
            errorFieldList.add(new ErrorField("", "El valor del campo coddoctor no existe"));
        }
    }
}
