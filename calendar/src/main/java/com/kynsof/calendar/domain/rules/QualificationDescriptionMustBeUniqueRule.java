package com.kynsof.calendar.domain.rules;

import com.kynsof.calendar.domain.dto.QualificationDto;
import com.kynsof.calendar.domain.service.IQualificationService;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.share.core.domain.rules.BusinessRule;

public class QualificationDescriptionMustBeUniqueRule extends BusinessRule {

    private final IQualificationService service;

    private QualificationDto qualification;

    public QualificationDescriptionMustBeUniqueRule(IQualificationService service, QualificationDto qualification) {
        super(DomainErrorMessage.QUALIFICATION_DESCRIPTION_UNIQUE,
                new ErrorField("Description",
                        "Qualification description unique!"));
        this.service = service;
        this.qualification = qualification;
    }

    @Override
    public boolean isBroken() {
        Long cant = service.countByDescription(qualification.getDescription());
        return cant > 0;
    }

}
