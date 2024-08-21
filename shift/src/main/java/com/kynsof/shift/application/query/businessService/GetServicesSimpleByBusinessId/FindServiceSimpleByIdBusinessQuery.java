package com.kynsof.shift.application.query.businessService.GetServicesSimpleByBusinessId;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

@Getter
public class FindServiceSimpleByIdBusinessQuery implements IQuery {

    private final UUID id;
    private final Pageable pageable;

    public FindServiceSimpleByIdBusinessQuery(UUID id, Pageable pageable) {
        this.id = id;
        this.pageable = pageable;
    }

}
