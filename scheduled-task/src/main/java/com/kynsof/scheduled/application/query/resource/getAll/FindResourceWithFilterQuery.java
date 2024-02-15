package com.kynsof.scheduled.application.query.resource.getAll;

import com.kynsof.share.core.domain.bus.query.IQuery;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@AllArgsConstructor
public class FindResourceWithFilterQuery implements IQuery {

    private Pageable pageable;
    private UUID idQualification;
    private String filter;

}
