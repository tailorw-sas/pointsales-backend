package com.kynsof.patients.domain.bus.query;

public interface IQueryHandler<Q extends IQuery, R extends IResponse> {
    R handle(Q query);
}
