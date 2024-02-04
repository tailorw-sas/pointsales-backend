package com.kynsof.patients.infrastructure.entity.specifications;

import com.kynsof.patients.infrastructure.entity.AdditionalInformation;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AdditionalInfoSpecifications implements Specification<AdditionalInformation> {
    private final UUID patientId;
    private final String emergencyContactName;

    public AdditionalInfoSpecifications(UUID patients, String emergencyContactName) {
        this.patientId = patients;
        this.emergencyContactName = emergencyContactName;
    }

    @Override
    public Predicate toPredicate(Root<AdditionalInformation> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (patientId != null) {
            predicates.add(cb.equal(root.get("patient_id"), patientId));
        }

        if (StringUtils.isNotEmpty(emergencyContactName)) {
            predicates.add(cb.equal(root.get("emergency_contact_name"), emergencyContactName));
        }

        return cb.and(predicates.toArray(Predicate[]::new));
    }
}
