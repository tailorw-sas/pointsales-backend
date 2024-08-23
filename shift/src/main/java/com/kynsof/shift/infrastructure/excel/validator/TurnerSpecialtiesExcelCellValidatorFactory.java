package com.kynsof.shift.infrastructure.excel.validator;

import com.kynsof.share.core.application.excel.validator.ExcelRuleValidator;
import com.kynsof.shift.domain.excel.TurnerSpecialtiesExcelRow;
import com.kynsof.shift.domain.service.IResourceService;
import com.kynsof.shift.domain.service.IServiceService;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class TurnerSpecialtiesExcelCellValidatorFactory {

private final IResourceService resourceService;
private final IServiceService serviceService;

    public TurnerSpecialtiesExcelCellValidatorFactory(IResourceService resourceService,
                                                      IServiceService serviceService) {
        this.resourceService = resourceService;
        this.serviceService = serviceService;
    }

    public ExcelRuleValidator<TurnerSpecialtiesExcelRow> makeExcelRuleValidator(String type){
        switch (type){
            case "COD_DOCTOR_VALIDATOR":{
                return new TurnerSpecialtiesCodDoctorCellValidator(resourceService);
            }
            case "COD_SERVICE_VALIDATOR":{
                return new TurnerSpecialtiesServicesCellValidator(serviceService);
            }
            default: throw new NoSuchElementException();
        }
    }
}
