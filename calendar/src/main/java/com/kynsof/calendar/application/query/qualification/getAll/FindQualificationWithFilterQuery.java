package com.kynsof.calendar.application.query.qualification.getAll;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class FindQualificationWithFilterQuery implements IQuery {

    private Pageable pageable;
    private UUID idQualification;
    private String filter;

}
