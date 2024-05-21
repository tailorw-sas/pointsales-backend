package com.kynsof.treatments.domain.rules.externalconsultation;

import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.share.core.domain.rules.BusinessRule;
import com.kynsof.share.utils.ConfigureTimeZone;

import java.time.*;
import java.util.Date;

public class ExternalConsultationCreateAtNotEqualsRule extends BusinessRule {

    private final LocalDateTime date;

    public ExternalConsultationCreateAtNotEqualsRule(Date consultationTime) {
        super(
                DomainErrorMessage.PROCEDURE_CODE_MUST_BY_UNIQUE,
                new ErrorField("date", "The external query cannot be modified. Creation date has already passed.")
        );
        this.date = ConfigureTimeZone.convertDateToLocalDateTime(consultationTime);
    }

    @Override
    public boolean isBroken() {
        return ConfigureTimeZone.validateEqualsDate(date);
    }
}
