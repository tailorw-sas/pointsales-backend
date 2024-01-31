package com.kynsof.patients.domain.bus;


import com.kynsof.patients.domain.bus.command.ICommand;
import com.kynsof.patients.domain.bus.command.ICommandMessage;
import com.kynsof.patients.domain.bus.query.IQuery;
import com.kynsof.patients.domain.bus.query.IResponse;

public interface IMediator {
    <M extends ICommandMessage> M send(ICommand command);

    <R extends IResponse> R send(IQuery query);
}
