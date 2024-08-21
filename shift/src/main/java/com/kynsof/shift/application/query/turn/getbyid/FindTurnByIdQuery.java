package com.kynsof.shift.application.query.turn.getbyid;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FindTurnByIdQuery implements IQuery {

    private final UUID id;

    public FindTurnByIdQuery(UUID id) {
        this.id = id;
    }

}
