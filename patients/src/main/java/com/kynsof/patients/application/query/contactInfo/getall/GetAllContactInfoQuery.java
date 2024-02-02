package com.kynsof.patients.application.query.contactInfo.getall;

import com.kynsof.patients.domain.bus.query.IQuery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class GetAllContactInfoQuery implements IQuery {

    private Pageable pageable;
    private UUID idPatients;
    private String email;
    private String phone;

}
