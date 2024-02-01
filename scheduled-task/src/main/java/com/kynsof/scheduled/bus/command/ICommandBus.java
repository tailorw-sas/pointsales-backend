package com.kynsof.scheduled.bus.command;

public interface ICommandBus {
    void dispatch(ICommand command) throws CommandHandlerExecutionError;
}
