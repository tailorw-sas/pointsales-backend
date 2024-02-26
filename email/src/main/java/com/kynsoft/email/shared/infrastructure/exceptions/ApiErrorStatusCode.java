package com.kynsoft.email.shared.infrastructure.exceptions;

import java.io.Serializable;

public sealed interface ApiErrorStatusCode extends Serializable permits ApiErrorStatus {
    /**
     * Return the integer value of this status code.
     */
    int value();

    /**
     * Whether this status code is in the Domain class ({@code 6xx}).
     */
    boolean is6xxDomainError();
}
