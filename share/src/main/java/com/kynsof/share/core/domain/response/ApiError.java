package com.kynsof.share.core.domain.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ApiError {
    private String errorMessage;
    private List<ErrorField> errors;

    public ApiError() {
    }

    public ApiError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public static ApiError withSingleError(String errorMessage, String field, String message) {
        ApiError apiError = new ApiError(errorMessage);
        ErrorField error = new ErrorField(field, message);
        apiError.setErrors(java.util.Collections.singletonList(error));
        return apiError;
    }
}