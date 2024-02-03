package com.kynsof.patients.application.query.patients.getall;

import com.kynsof.patients.domain.bus.query.IQuery;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@AllArgsConstructor
public class GetAllPatientsFilterQuery implements IQuery {

    private Pageable pageable;
    private UUID idPatients;
    private String identification;

}