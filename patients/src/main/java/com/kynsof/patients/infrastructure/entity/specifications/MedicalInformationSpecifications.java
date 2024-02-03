package com.kynsof.patients.infrastructure.entity.specifications;

import com.kynsof.patients.infrastructure.entity.AdditionalInformation;
import com.kynsof.patients.infrastructure.entity.MedicalInformation;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MedicalInformationSpecifications implements Specification<MedicalInformation> {
    private final UUID patientId;


    public MedicalInformationSpecifications(UUID patients) {
        this.patientId = patients;
    }

    @Override
    public Predicate toPredicate(Root<MedicalInformation> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (patientId != null) {
            predicates.add(cb.equal(root.get("patient_id"), patientId));
        }

        return cb.and(predicates.toArray(Predicate[]::new));
    }
}
