package com.kynsof.calendar.infrastructure.entity.specifications;

import com.kynsof.calendar.infrastructure.entity.Qualification;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class QualificationSpecifications implements Specification<Qualification> {
    private UUID qualification;
    private String filter;

    public QualificationSpecifications(UUID qualification, String filter) {
        this.qualification = qualification;
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<Qualification> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (qualification != null) {
            predicates.add(cb.equal(root.get("id"), qualification));
        }

        return cb.and(predicates.toArray(Predicate[]::new));
    }
}
