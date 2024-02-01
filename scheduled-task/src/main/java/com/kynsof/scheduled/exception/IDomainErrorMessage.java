package com.kynsof.scheduled.exception;

import java.io.Serializable;

public sealed interface IDomainErrorMessage extends Serializable permits DomainErrorMessage {
    /**
     * Return the integer value of this status code.
     */
    int value();
}
