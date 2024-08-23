package com.kynsof.shift.infrastructure.excel.validator;

import com.kynsof.share.core.application.excel.validator.ExcelRuleValidator;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.shift.domain.excel.TurnerSpecialtiesExcelRow;
import com.kynsof.shift.domain.service.IResourceService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TurnerSpecialtiesCodDoctorCellValidator implements ExcelRuleValidator<TurnerSpecialtiesExcelRow> {

    public static final String VALIDATOR_ID="COD_SERVICE_VALIDATOR";
    private final IResourceService resourceService;

    public TurnerSpecialtiesCodDoctorCellValidator(IResourceService resourceService) {
        this.resourceService = resourceService;
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
        if (Objects.nonNull(row.getCodDoctor()) && resourceService.existResourceByCode(row.getCodDoctor())) {
            errorFieldList.add(new ErrorField("", "El valor del campo coddoctor no existe"));
        }
    }
}
