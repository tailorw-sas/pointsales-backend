package com.kynsof.calendar.application.query.receipt.getbyid;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FindReceiptByIdQuery  implements IQuery {

    private UUID id;

    public FindReceiptByIdQuery(UUID id) {
        this.id = id;
    }

}
