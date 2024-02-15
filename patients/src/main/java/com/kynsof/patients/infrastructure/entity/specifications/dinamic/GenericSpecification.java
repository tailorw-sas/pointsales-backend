package com.kynsof.patients.infrastructure.entity.specifications.dinamic;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import java.util.List;

public class GenericSpecification<T> implements Specification<T> {
    private final SearchCriteria criteria;

    public GenericSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        return switch (criteria.getOperation()) {
            case LIKE -> builder.like(builder.lower(root.get(criteria.getKey())), "%" + criteria.getValue().toString().toLowerCase() + "%");
            case EQUALS -> builder.equal(root.get(criteria.getKey()), criteria.getValue());
            case GREATER_THAN -> builder.greaterThan(root.get(criteria.getKey()), (Comparable) criteria.getValue());
            case LESS_THAN -> builder.lessThan(root.get(criteria.getKey()), (Comparable) criteria.getValue());
            case GREATER_THAN_OR_EQUAL_TO -> builder.greaterThanOrEqualTo(root.get(criteria.getKey()), (Comparable) criteria.getValue());
            case LESS_THAN_OR_EQUAL_TO -> builder.lessThanOrEqualTo(root.get(criteria.getKey()), (Comparable) criteria.getValue());
            case NOT_EQUALS -> builder.notEqual(root.get(criteria.getKey()), criteria.getValue());
            case IN-> {
                CriteriaBuilder.In<Object> inClause = builder.in(root.get(criteria.getKey()));
                List<?> values = (List<?>) criteria.getValue();
                values.forEach(inClause::value);
                yield inClause;
            }
            case NOT_IN -> {
                CriteriaBuilder.In<Object> inClause = builder.in(root.get(criteria.getKey()));
                List<?> values = (List<?>) criteria.getValue();
                values.forEach(inClause::value);
                yield builder.not(inClause);
            }
            case IS_NULL -> builder.isNull(root.get(criteria.getKey()));
            case IS_NOT_NULL -> builder.isNotNull(root.get(criteria.getKey()));
            case IS_TRUE -> builder.isTrue(root.get(criteria.getKey()));
            case IS_FALSE -> builder.isFalse(root.get(criteria.getKey()));
            default -> null;
        };
    }
}
