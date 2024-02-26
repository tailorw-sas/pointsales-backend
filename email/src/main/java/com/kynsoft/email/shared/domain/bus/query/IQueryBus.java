package com.kynsoft.email.shared.domain.bus.query;

public interface IQueryBus {
    <R> R ask(IQuery query) throws QueryHandlerExecutionError;
}
