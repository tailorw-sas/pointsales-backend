package com.kynsof.treatments.infrastructure.entity.specifications;

import com.kynsof.treatments.infrastructure.entity.ExamOrder;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ExamOrderSpecifications implements Specification<ExamOrder> {
    private final UUID patientId;

    public ExamOrderSpecifications(UUID patientId) {
        this.patientId = patientId;
    }

    @Override
    public Predicate toPredicate(Root<ExamOrder> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (patientId != null) {
            predicates.add(cb.equal(root.get("patient_id"), patientId));
        }

        return cb.and(predicates.toArray(Predicate[]::new));
    }
}
