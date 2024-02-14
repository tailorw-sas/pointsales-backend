package com.kynsof.patients.infrastructure.entity.specifications.dinamic;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class GenericSpecificationsBuilder<T> implements Specification<T> {
    private final List<SearchCriteria> params;

    public GenericSpecificationsBuilder(List<SearchCriteria> params) {
        this.params = params;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        for (SearchCriteria criteria : params) {
            Predicate predicate = new GenericSpecification<T>(criteria).toPredicate(root, query, cb);
            if (predicate != null) {
                predicates.add(predicate);
            }
        }
        return cb.and(predicates.toArray(new Predicate[0]));
    }
}