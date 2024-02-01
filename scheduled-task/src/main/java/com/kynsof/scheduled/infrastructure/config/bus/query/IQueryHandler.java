package com.kynsof.scheduled.infrastructure.config.bus.query;

public interface IQueryHandler<Q extends IQuery, R extends IResponse> {
    R handle(Q query);
}
