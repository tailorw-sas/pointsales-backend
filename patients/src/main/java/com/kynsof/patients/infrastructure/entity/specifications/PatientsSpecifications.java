package com.kynsof.patients.infrastructure.entity.specifications;

import com.kynsof.patients.infrastructure.entity.Patients;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class PatientsSpecifications implements Specification<Patients> {
    private final UUID patients;
    private final String identification;
    private final UUID primeId;

    public PatientsSpecifications(UUID patients, String identification, UUID primeId) {
        this.patients = patients;
        this.identification = identification;
        this.primeId = primeId;
    }

    @Override
    public Predicate toPredicate(Root<Patients> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (patients != null) {
            predicates.add(cb.equal(root.get("id"), patients));
        }

        if (primeId != null) {
            predicates.add(cb.equal(root.join("prime").get("id"), primeId));
        }

        if (StringUtils.isNotEmpty(identification)) {
            predicates.add(cb.equal(root.get("identification"), identification));
        }

        return cb.and(predicates.toArray(Predicate[]::new));
    }
}
