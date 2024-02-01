package com.kynsof.patients.infrastructure.entity.specifications;

import com.kynsof.patients.infrastructure.entity.PatientsDAO;
import org.springframework.data.jpa.domain.Specification;

public class PatientsSpecs {

    public static Specification<PatientsDAO> getidentification(String identification) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("identification"), identification));
    }
}
