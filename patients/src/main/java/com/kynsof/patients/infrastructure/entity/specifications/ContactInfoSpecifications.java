package com.kynsof.patients.infrastructure.entity.specifications;

import com.kynsof.patients.infrastructure.entity.ContactInformation;
import com.kynsof.patients.infrastructure.entity.Patients;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ContactInfoSpecifications implements Specification<ContactInformation> {
    private final UUID patientId;
    private final String email;

    public ContactInfoSpecifications(UUID patients, String email) {
        this.patientId = patients;
        this.email = email;
    }

    @Override
    public Predicate toPredicate(Root<ContactInformation> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (patientId != null) {
            predicates.add(cb.equal(root.get("patient_id"), patientId));
        }

        if (StringUtils.isNotEmpty(email)) {
            predicates.add(cb.equal(root.get("email"), email));
        }

        return cb.and(predicates.toArray(Predicate[]::new));
    }
}
