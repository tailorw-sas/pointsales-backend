package com.kynsof.share.core.domain.exception;

import com.kynsof.share.core.domain.response.ApiError;
import com.kynsof.share.core.domain.response.ApiResponse;
import com.kynsof.share.core.domain.response.ErrorField;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        ApiError apiError = new ApiError(ex.getMessage(), null); // Assuming constructor ApiError(message, errors)
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.fail(apiError));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        List<ErrorField> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new ErrorField(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        ApiError apiError = new ApiError("Validation Error", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.fail(apiError));
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse<?>> handleCustomException(CustomException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ApiResponse.fail(ex.getApiError()));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // This annotation ensures a 500 status is returned.
    public ResponseEntity<ApiResponse<?>> handleAllUncaughtException(Exception ex, WebRequest request) {
        ApiError apiError = new ApiError("An unexpected error occurred.", List.of(new ErrorField("internal", "Internal server error.")));
        return ResponseEntity.internalServerError().body(ApiResponse.fail(apiError));
    }
}
