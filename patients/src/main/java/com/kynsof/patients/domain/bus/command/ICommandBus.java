package com.kynsof.patients.domain.bus.command;

public interface ICommandBus {
    void dispatch(ICommand command) throws CommandHandlerExecutionError;
}
