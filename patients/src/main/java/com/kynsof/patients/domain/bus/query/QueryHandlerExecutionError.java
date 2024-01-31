package com.kynsof.patients.domain.bus.query;

public final class QueryHandlerExecutionError extends RuntimeException {
    public QueryHandlerExecutionError(Throwable cause) {
        super(cause);
    }
}
