package com.kynsof.calendar.domain.rules;

import com.kynsof.calendar.domain.dto.QualificationDto;
import com.kynsof.calendar.domain.service.IQualificationService;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.share.core.domain.rules.BusinessRule;

public class QualificationDescriptionMustBeNotNullRule extends BusinessRule {

    private final IQualificationService service;

    private QualificationDto qualification;

    public QualificationDescriptionMustBeNotNullRule(IQualificationService service, QualificationDto qualification) {
        super(DomainErrorMessage.QUALIFICATION_DESCRIPTION_NOT_NULL,
                new ErrorField("Description",
                        "Qualification description not null!")
        );
        this.service = service;
        this.qualification = qualification;
    }

    @Override
    public boolean isBroken() {
        return null == qualification.getDescription()
                || qualification.getDescription().isBlank()
                || qualification.getDescription().isEmpty();
    }

}
