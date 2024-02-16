package com.kynsof.calendar.application.command.resource.delete;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResourceDeleteCommand implements ICommand {

    private UUID id;

    @Override
    public ICommandMessage getMessage() {
        return new ResourceDeleteMessage(id);
    }

}
