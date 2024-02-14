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
        return switch (criteria.operation().toLowerCase()) {
            case "like" -> builder.like(builder.lower(root.get(criteria.key())), "%" + criteria.value().toString().toLowerCase() + "%");
            case "equals" -> builder.equal(root.get(criteria.key()), criteria.value());
            case "greater-than" -> builder.greaterThan(root.get(criteria.key()), (Comparable) criteria.value());
            case "less-than" -> builder.lessThan(root.get(criteria.key()), (Comparable) criteria.value());
            case "greater-than-or-equal-to" -> builder.greaterThanOrEqualTo(root.get(criteria.key()), (Comparable) criteria.value());
            case "less-than-or-equal-to" -> builder.lessThanOrEqualTo(root.get(criteria.key()), (Comparable) criteria.value());
            case "not-equal" -> builder.notEqual(root.get(criteria.key()), criteria.value());
            case "in" -> {
                CriteriaBuilder.In<Object> inClause = builder.in(root.get(criteria.key()));
                List<?> values = (List<?>) criteria.value();
                values.forEach(inClause::value);
                yield inClause;
            }
            case "not-in" -> {
                CriteriaBuilder.In<Object> inClause = builder.in(root.get(criteria.key()));
                List<?> values = (List<?>) criteria.value();
                values.forEach(inClause::value);
                yield builder.not(inClause);
            }
            case "is-null" -> builder.isNull(root.get(criteria.key()));
            case "is-notnull" -> builder.isNotNull(root.get(criteria.key()));
            case "is-true" -> builder.isTrue(root.get(criteria.key()));
            case "is-false" -> builder.isFalse(root.get(criteria.key()));
            default -> null;
        };
    }
}
