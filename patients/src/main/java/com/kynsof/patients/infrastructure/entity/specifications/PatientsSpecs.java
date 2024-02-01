package com.kynsof.patients.infrastructure.entity.specifications;

import com.kynsof.patients.infrastructure.entity.Patients;
import org.springframework.data.jpa.domain.Specification;

public class PatientsSpecs {

    public static Specification<Patients> getidentification(String identification) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("identification"), identification));
    }
}
