package com.kynsoft.gateway.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {
    private String errorMessage;
    private int status;
    private List<ErrorField> errors;

    // Constructor, getters y setters
}
