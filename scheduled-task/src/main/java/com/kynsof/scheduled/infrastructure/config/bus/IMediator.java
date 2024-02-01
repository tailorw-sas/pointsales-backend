package com.kynsof.scheduled.infrastructure.config.bus;


import com.kynsof.scheduled.infrastructure.config.bus.command.ICommand;
import com.kynsof.scheduled.infrastructure.config.bus.command.ICommandMessage;
import com.kynsof.scheduled.infrastructure.config.bus.query.IQuery;
import com.kynsof.scheduled.infrastructure.config.bus.query.IResponse;

public interface IMediator {
    <M extends ICommandMessage> M send(ICommand command);

    <R extends IResponse> R send(IQuery query);
}
