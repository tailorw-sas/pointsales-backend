package com.kynsoft.notification.shared.domain.bus.command;

public interface ICommandHandler<T extends ICommand, S> {
    S handle(T command);
}
