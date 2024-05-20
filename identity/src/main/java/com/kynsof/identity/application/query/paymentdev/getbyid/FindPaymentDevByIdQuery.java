package com.kynsof.identity.application.query.paymentdev.getbyid;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FindPaymentDevByIdQuery  implements IQuery {

    private final UUID id;

    public FindPaymentDevByIdQuery(UUID id) {
        this.id = id;
    }

}
