package com.kynsof.patients.application.query.geographicLocation.getall;

import com.kynsof.patients.domain.bus.query.IQuery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class GetAllGeographicLocationQuery implements IQuery {

    private Pageable pageable;
    private UUID parentId;
    private String name;
    private String type;

}
