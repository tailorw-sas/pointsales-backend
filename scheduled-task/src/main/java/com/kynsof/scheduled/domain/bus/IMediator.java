package com.kynsof.scheduled.domain.bus;


import com.kynsof.scheduled.domain.bus.command.ICommand;
import com.kynsof.scheduled.domain.bus.command.ICommandMessage;
import com.kynsof.scheduled.domain.bus.query.IQuery;
import com.kynsof.scheduled.domain.bus.query.IResponse;

public interface IMediator {
    <M extends ICommandMessage> M send(ICommand command);

    <R extends IResponse> R send(IQuery query);
}
