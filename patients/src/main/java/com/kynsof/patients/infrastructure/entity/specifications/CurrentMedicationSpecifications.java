package com.kynsof.patients.infrastructure.entity.specifications;

import com.kynsof.patients.infrastructure.entity.CurrentMedication;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CurrentMedicationSpecifications implements Specification<CurrentMedication> {
    private final UUID medicationInformationId;
    private final String name;

    public CurrentMedicationSpecifications(UUID medicalInformationId, String name) {
        this.medicationInformationId = medicalInformationId;
        this.name = name;
    }

    @Override
    public Predicate toPredicate(Root<CurrentMedication> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (medicationInformationId != null) {
            predicates.add(cb.equal(root.get("medical_information_id"), medicationInformationId));
        }

        if (StringUtils.isNotEmpty(name)) {
            predicates.add(cb.equal(root.get("name"), name));
        }

        return cb.and(predicates.toArray(Predicate[]::new));
    }
}
