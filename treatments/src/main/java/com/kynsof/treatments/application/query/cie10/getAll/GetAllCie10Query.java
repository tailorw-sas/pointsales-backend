package com.kynsof.treatments.application.query.cie10.getAll;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@AllArgsConstructor
public class GetAllCie10Query implements IQuery {

    private Pageable pageable;
    private String name;
    private String code;

}
