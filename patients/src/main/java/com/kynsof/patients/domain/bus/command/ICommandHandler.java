package com.kynsof.patients.domain.bus.command;

public interface ICommandHandler<T extends ICommand> {
    void handle(T command);
}
