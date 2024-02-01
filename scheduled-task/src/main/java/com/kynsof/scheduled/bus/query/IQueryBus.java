package com.kynsof.scheduled.bus.query;

public interface IQueryBus {
    <R> R ask(IQuery query) throws QueryHandlerExecutionError;
}
