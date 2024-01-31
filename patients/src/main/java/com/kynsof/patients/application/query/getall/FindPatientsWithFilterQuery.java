package com.kynsof.patients.application.query.getall;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@AllArgsConstructor
public class FindPatientsWithFilterQuery {

    private Pageable pageable;

}
