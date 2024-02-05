package com.kynsof.scheduled.application.query.service.getAll;

import com.kynsof.scheduled.infrastructure.config.bus.query.IQuery;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@AllArgsConstructor
public class FindServiceWithFilterQuery implements IQuery {

    private Pageable pageable;
    private UUID idObject;
    private String filter;

}
