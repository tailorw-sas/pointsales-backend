package com.kynsoft.notification.shared.domain.service;

import org.springframework.scheduling.annotation.Async;

@Async
public interface IEventService<T> {
    public void create(T entity);

    public void update(T entity);

    public void delete(T entity);
}
