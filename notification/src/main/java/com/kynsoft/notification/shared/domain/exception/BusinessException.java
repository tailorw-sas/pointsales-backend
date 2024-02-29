package com.kynsoft.notification.shared.domain.exception;

import com.kynsoft.notification.shared.infrastructure.exceptions.ApiErrorStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BusinessException extends RuntimeException {

    private final int status;

    private final String message;

    private final String details;

    public BusinessException(ApiErrorStatus status, String details) {
        super(status.getReasonPhrase());
        this.status = 800;
        this.message = status.getReasonPhrase();
        this.details = details;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

}
