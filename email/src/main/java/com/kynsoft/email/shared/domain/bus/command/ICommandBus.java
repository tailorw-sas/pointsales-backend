package com.kynsoft.email.shared.domain.bus.command;

public interface ICommandBus {
    void dispatch(ICommand command) throws CommandHandlerExecutionError;
}
