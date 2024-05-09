package com.kynsof.treatments.domain.rules.externalconsultation;

import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.share.core.domain.rules.BusinessRule;
import com.kynsof.share.utils.ConfigureTimeZone;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class ExternalConsultationCreateAtNotEqualsRule extends BusinessRule {

    private final LocalDateTime date;

    public ExternalConsultationCreateAtNotEqualsRule(Date consultationTime) {
        super(
                DomainErrorMessage.PROCEDURE_CODE_MUST_BY_UNIQUE,
                new ErrorField("date", "The external query cannot be modified. Creation date has already passed.")
        );
        this.date = convertDateToLocalDateTime(consultationTime);
    }

    @Override
    public boolean isBroken() {
        LocalDateTime today = ConfigureTimeZone.getTimeZone();
        LocalDate actual = today.toLocalDate();
        LocalDate save = this.date.toLocalDate();

        return !save.equals(actual);
    }

    private LocalDateTime convertDateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
        LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();

        return localDateTime;
    }
}
