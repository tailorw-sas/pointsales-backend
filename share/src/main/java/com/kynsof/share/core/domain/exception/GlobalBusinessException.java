package com.kynsof.share.core.domain.exception;

import com.kynsof.share.core.domain.response.ErrorField;
import lombok.Getter;

@Getter
public class GlobalBusinessException {

    private final DomainErrorMessage error;

    private final ErrorField errorField;

    public GlobalBusinessException(DomainErrorMessage error, ErrorField errorField) {
        this.error = error;
        this.errorField = errorField;
    }

}
