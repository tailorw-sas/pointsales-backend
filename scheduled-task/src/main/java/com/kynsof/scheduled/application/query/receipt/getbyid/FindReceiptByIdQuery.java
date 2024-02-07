package com.kynsof.scheduled.application.query.receipt.getbyid;

import com.kynsof.scheduled.infrastructure.config.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FindReceiptByIdQuery  implements IQuery {

    private UUID id;

    public FindReceiptByIdQuery(UUID id) {
        this.id = id;
    }

}
