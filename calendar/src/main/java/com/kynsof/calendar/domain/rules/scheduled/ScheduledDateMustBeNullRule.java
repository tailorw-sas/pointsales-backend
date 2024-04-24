package com.kynsof.calendar.domain.rules.scheduled;

import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.response.ErrorField;
import com.kynsof.share.core.domain.rules.BusinessRule;
import java.time.LocalDate;

public class ScheduledDateMustBeNullRule extends BusinessRule {

    private final LocalDate date;

    public ScheduledDateMustBeNullRule(LocalDate date, String field, String msg) {
        super(
                DomainErrorMessage.SCHEDULED_DATE_IS_NOT_PRESENT, 
                new ErrorField(field, msg)
        );
        this.date = date;
    }

    @Override
    public boolean isBroken() {
        return this.date == null;
    }
    
}
