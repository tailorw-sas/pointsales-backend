package com.kynsof.patients.infrastructure.entity.specifications;

import com.kynsof.patients.infrastructure.entity.Allergy;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AllergySpecifications implements Specification<Allergy> {
    private final UUID medicationInformationId;
    private final String name;
    private final String code;

    public AllergySpecifications(UUID medicalInformationId, String name, String code) {
        this.medicationInformationId = medicalInformationId;
        this.name = name;
        this.code = code;
    }

    @Override
    public Predicate toPredicate(Root<Allergy> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (medicationInformationId != null) {
            predicates.add(cb.equal(root.get("medical_information_id"), medicationInformationId));
        }

        if (StringUtils.isNotEmpty(name)) {
            predicates.add(cb.equal(root.get("name"), name));
        }

        if (StringUtils.isNotEmpty(code)) {
            predicates.add(cb.equal(root.get("code"), code));
        }

        return cb.and(predicates.toArray(Predicate[]::new));
    }
}
