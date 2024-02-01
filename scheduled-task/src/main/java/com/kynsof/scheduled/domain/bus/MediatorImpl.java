package com.kynsof.scheduled.domain.bus;
import com.kynsof.scheduled.domain.bus.command.ICommand;
import com.kynsof.scheduled.domain.bus.command.ICommandMessage;
import com.kynsof.scheduled.domain.bus.command.InMemoryCommandBus;
import com.kynsof.scheduled.domain.bus.query.IQuery;
import com.kynsof.scheduled.domain.bus.query.IResponse;
import com.kynsof.scheduled.domain.bus.query.InMemoryQueryBus;
import org.springframework.stereotype.Component;

@Component
public class MediatorImpl implements IMediator {

    private final InMemoryCommandBus commandBus;

    private final InMemoryQueryBus queryBus;

    public MediatorImpl(InMemoryCommandBus commandBus, InMemoryQueryBus queryBus) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
    }

    @Override
    public <M extends ICommandMessage> M send(ICommand command) {
        commandBus.dispatch(command);
        return (M) command.getMessage();
    }

    @Override
    public <R extends IResponse> R send(IQuery query) {
        return (R) queryBus.ask(query);
    }

}
