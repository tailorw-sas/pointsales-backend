package com.kynsof.calendar.application.query.businessService.getresourcebybusiness;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

@Getter
public class FindResourcesByIdBusinessQuery  implements IQuery {

    private UUID id;
    private Pageable pageable;

    public FindResourcesByIdBusinessQuery(UUID id, Pageable pageable) {
        this.id = id;
        this.pageable = pageable;
    }

}
