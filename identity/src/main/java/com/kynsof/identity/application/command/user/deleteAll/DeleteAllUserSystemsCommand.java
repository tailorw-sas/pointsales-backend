package com.kynsof.identity.application.command.user.deleteAll;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class DeleteAllUserSystemsCommand implements ICommand {

    private List<UUID> ids;

    @Override
    public ICommandMessage getMessage() {
        return new DeleteAllUserSystemsMessage();
    }

}
