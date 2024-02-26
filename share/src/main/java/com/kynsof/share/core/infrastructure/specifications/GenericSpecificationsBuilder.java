package com.kynsof.share.core.infrastructure.specifications;

import com.kynsof.share.core.domain.request.FilterCriteria;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GenericSpecificationsBuilder<T> implements Specification<T> {
    private final List<SearchCriteria> params;

    public GenericSpecificationsBuilder(List<FilterCriteria> filterCriteria) {
        this.params = filterCriteria.stream()
                .map(filterCriteriaItem -> new SearchCriteria(filterCriteriaItem.getKey(),
                        filterCriteriaItem.getOperator(), filterCriteriaItem.getValue()))
                .collect(Collectors.toList());
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
        return cb.and(predicates.toArray(Predicate[]::new));
    }
}