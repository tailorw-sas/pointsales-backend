package com.kynsof.scheduled.bus.query;

public interface IQueryHandler<Q extends IQuery, R extends IResponse> {
    R handle(Q query);
}
