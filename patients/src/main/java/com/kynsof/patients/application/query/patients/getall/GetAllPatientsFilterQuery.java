package com.kynsof.patients.application.query.patients.getall;

import com.kynsof.share.core.domain.bus.query.IQuery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class GetAllPatientsFilterQuery implements IQuery {

    private Pageable pageable;
    private UUID idPatients;
    private String identification;
    private UUID primeId;

}
