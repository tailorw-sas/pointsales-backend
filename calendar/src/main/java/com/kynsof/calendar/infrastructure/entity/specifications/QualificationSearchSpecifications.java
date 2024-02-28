package com.kynsof.calendar.infrastructure.entity.specifications;

import com.kynsof.calendar.infrastructure.entity.Qualification;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class QualificationSearchSpecifications implements Specification<Qualification> {

    @Override
    public Predicate toPredicate(Root<Qualification> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(cb.isFalse(root.get("deleted")));

        return cb.and(predicates.toArray(Predicate[]::new));
    }
}
