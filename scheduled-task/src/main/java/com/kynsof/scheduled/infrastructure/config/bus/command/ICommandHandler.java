package com.kynsof.scheduled.infrastructure.config.bus.command;

public interface ICommandHandler<T extends ICommand> {
    void handle(T command);
}
