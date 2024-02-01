package com.kynsof.scheduled.bus.query;

public final class QueryHandlerExecutionError extends RuntimeException {
    public QueryHandlerExecutionError(Throwable cause) {
        super(cause);
    }
}
