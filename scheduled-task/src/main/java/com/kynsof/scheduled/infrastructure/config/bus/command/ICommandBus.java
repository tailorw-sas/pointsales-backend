package com.kynsof.scheduled.infrastructure.config.bus.command;

public interface ICommandBus {
    void dispatch(ICommand command) throws CommandHandlerExecutionError;
}
