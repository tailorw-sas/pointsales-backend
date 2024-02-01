package com.kynsof.scheduled.bus;


import com.kynsof.scheduled.bus.command.ICommand;
import com.kynsof.scheduled.bus.command.ICommandMessage;
import com.kynsof.scheduled.bus.query.IQuery;
import com.kynsof.scheduled.bus.query.IResponse;

public interface IMediator {
    <M extends ICommandMessage> M send(ICommand command);

    <R extends IResponse> R send(IQuery query);
}
