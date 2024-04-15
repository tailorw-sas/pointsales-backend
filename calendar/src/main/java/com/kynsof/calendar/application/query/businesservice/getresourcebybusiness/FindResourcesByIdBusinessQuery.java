package com.kynsof.calendar.application.query.businesservice.getresourcebybusiness;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.Getter;

import java.util.UUID;
import org.springframework.data.domain.Pageable;

@Getter
public class FindResourcesByIdBusinessQuery  implements IQuery {

    private UUID id;
    private Pageable pageable;

    public FindResourcesByIdBusinessQuery(UUID id, Pageable pageable) {
        this.id = id;
        this.pageable = pageable;
    }

}
