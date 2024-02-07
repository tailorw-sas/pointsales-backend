package com.kynsof.treatments.application.query.vaccine.getall;

import com.kynsof.treatments.domain.bus.query.IQuery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class GetAllVaccineQuery implements IQuery {

    private Pageable pageable;
    private String name;
    private String description;

}
