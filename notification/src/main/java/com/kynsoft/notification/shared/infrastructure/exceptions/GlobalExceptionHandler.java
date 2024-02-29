package com.kynsoft.notification.shared.infrastructure.exceptions;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.Nullable;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {

                List<String> details = new ArrayList<>();
                details.add(ex.getMessage());

                ApiError err = new ApiError(
                                ZonedDateTime.now(ZoneId.of("UTC")),
                                HttpStatus.BAD_REQUEST.value(),
                                "Resource Not Found",
                                details);

                return ResponseEntityBuilder.build(err);
        }

        @ExceptionHandler(MethodArgumentTypeMismatchException.class)
        protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
                        WebRequest request) {

                List<String> details = new ArrayList<>();
                details.add(ex.getMessage());

                ApiError err = new ApiError(
                                ZonedDateTime.now(ZoneId.of("UTC")),
                                HttpStatus.BAD_REQUEST,
                                "Type Mismatch",
                                details);

                return ResponseEntityBuilder.build(err);
        }

        @ExceptionHandler(ConstraintViolationException.class)
        protected ResponseEntity<Object> handleConstraintViolationException(MethodArgumentNotValidException ex,
                        WebRequest request) {

                List<String> details = new ArrayList<>();
                details.add(ex.getMessage());

                ApiError err = new ApiError(
                                ZonedDateTime.now(ZoneId.of("UTC")),
                                HttpStatus.BAD_REQUEST,
                                "Constraint Violations",
                                details);

                return ResponseEntityBuilder.build(err);
        }

        @Override
        @Nullable
        protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                        HttpHeaders headers, HttpStatusCode status, WebRequest request) {

                List<String> details = new ArrayList<>();
                details.add(ex.getMessage());

                ApiError err = new ApiError(
                                ZonedDateTime.now(ZoneId.of("UTC")),
                                HttpStatus.BAD_REQUEST,
                                "Malformed JSON request",
                                details);

                return ResponseEntityBuilder.build(err);
        }

        @Override
        @Nullable
        protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                        HttpHeaders headers, HttpStatusCode status, WebRequest request) {

                List<String> details;
                details = ex.getBindingResult()
                                .getFieldErrors()
                                .stream()
                                .map(error -> error.getObjectName() + " : " + error.getDefaultMessage())
                                .collect(Collectors.toList());

                ApiError err = new ApiError(
                                ZonedDateTime.now(ZoneId.of("UTC")),
                                HttpStatus.BAD_REQUEST,
                                "Validation Errors",
                                details);

                return ResponseEntityBuilder.build(err);
        }

        @Override
        @Nullable
        protected ResponseEntity<Object> handleMissingServletRequestParameter(
                        MissingServletRequestParameterException ex,
                        HttpHeaders headers, HttpStatusCode status, WebRequest request) {

                List<String> details = new ArrayList<>();
                details.add(ex.getParameterName() + " parameter is missing");

                ApiError err = new ApiError(
                                ZonedDateTime.now(ZoneId.of("UTC")),
                                HttpStatus.BAD_REQUEST,
                                "Missing Parameters",
                                details);

                return ResponseEntityBuilder.build(err);
        }

        @Override
        @Nullable
        protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
                        HttpHeaders headers, HttpStatusCode status, WebRequest request) {

                List<String> details = new ArrayList<>();
                StringBuilder builder = new StringBuilder();
                StringJoiner joiner = new StringJoiner(", ");

                builder.append(ex.getContentType());
                builder.append(" media type is not supported. Supported media types are ");
                ex.getSupportedMediaTypes().forEach(t -> joiner.add(t.toString()));
                builder.append(joiner.toString());

                details.add(builder.toString());

                ApiError err = new ApiError(
                                ZonedDateTime.now(ZoneId.of("UTC")),
                                HttpStatus.UNSUPPORTED_MEDIA_TYPE,
                                "Unsupported Media Type",
                                details);

                return ResponseEntityBuilder.build(err);
        }

        @Override
        @Nullable
        protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
                        HttpStatusCode status, WebRequest request) {

                List<String> details = new ArrayList<>();
                details.add(String.format("Could not find the %s method for URL %s", ex.getHttpMethod(),
                                ex.getRequestURL()));

                ApiError err = new ApiError(
                                ZonedDateTime.now(ZoneId.of("UTC")),
                                HttpStatus.NOT_FOUND,
                                "Method Not Found",
                                details);

                return ResponseEntityBuilder.build(err);
        }

        // Deal with ALL Other Exceptions
        @ExceptionHandler({ Exception.class })
        public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {

                List<String> details = new ArrayList<>();
                details.add(ex.getLocalizedMessage());

                ApiError err = new ApiError(
                                ZonedDateTime.now(ZoneId.of("UTC")),
                                HttpStatus.BAD_REQUEST,
                                "Error occurred",
                                details);

                return ResponseEntityBuilder.build(err);
        }
}
