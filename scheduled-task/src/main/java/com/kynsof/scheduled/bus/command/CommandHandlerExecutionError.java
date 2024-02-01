package com.kynsof.scheduled.bus.command;

public final class CommandHandlerExecutionError extends RuntimeException {
    public CommandHandlerExecutionError(Throwable cause) {
        super(cause);
    }
}
