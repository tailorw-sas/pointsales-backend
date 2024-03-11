package com.kynsof.share.core.infrastructure.specifications;

import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.UUID;

public class GenericSpecification<T> implements Specification<T> {
    private final SearchCriteria criteria;

    public GenericSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }
//
//    @Override
//    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
//        return switch (criteria.getOperation()) {
//            case LIKE -> builder.like(builder.lower(root.get(criteria.getKey())), "%" + criteria.getValue().toString().toLowerCase() + "%");
//            case EQUALS -> builder.equal(root.get(criteria.getKey()), criteria.getValue());
//            case GREATER_THAN -> builder.greaterThan(root.get(criteria.getKey()), (Comparable) criteria.getValue());
//            case LESS_THAN -> builder.lessThan(root.get(criteria.getKey()), (Comparable) criteria.getValue());
//            case GREATER_THAN_OR_EQUAL_TO -> builder.greaterThanOrEqualTo(root.get(criteria.getKey()), (Comparable) criteria.getValue());
//            case LESS_THAN_OR_EQUAL_TO -> builder.lessThanOrEqualTo(root.get(criteria.getKey()), (Comparable) criteria.getValue());
//            case NOT_EQUALS -> builder.notEqual(root.get(criteria.getKey()), criteria.getValue());
//            case IN-> {
//                CriteriaBuilder.In<Object> inClause = builder.in(root.get(criteria.getKey()));
//                List<?> values = (List<?>) criteria.getValue();
//                values.forEach(inClause::value);
//                yield inClause;
//            }
//            case NOT_IN -> {
//                CriteriaBuilder.In<Object> inClause = builder.in(root.get(criteria.getKey()));
//                List<?> values = (List<?>) criteria.getValue();
//                values.forEach(inClause::value);
//                yield builder.not(inClause);
//            }
//            case IS_NULL -> builder.isNull(root.get(criteria.getKey()));
//            case IS_NOT_NULL -> builder.isNotNull(root.get(criteria.getKey()));
//            case IS_TRUE -> builder.isTrue(root.get(criteria.getKey()));
//            case IS_FALSE -> builder.isFalse(root.get(criteria.getKey()));
//            default -> null;
//        };
//    }
@Override
public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
    Path<Object> path;

    if (criteria.getKey().contains(".")) {
        String[] keys = criteria.getKey().split("\\.");
        path = root.get(keys[0]);
        for (int i = 1; i < keys.length; i++) {
            path = path.get(keys[i]);
        }
    } else {
        path = root.get(criteria.getKey());
    }

    // Verificar si el valor es un UUID y convertirlo a UUID si es necesario.
    Object value = criteria.getValue();
    if (value instanceof String && isValidUUID((String) value)) {
        value = UUID.fromString((String) value);
    }

    return switch (criteria.getOperation()) {
        case LIKE -> builder.like(builder.lower(path.as(String.class)), "%" + value.toString().toLowerCase() + "%");
        case EQUALS -> builder.equal(path, value);
        case GREATER_THAN -> builder.greaterThan(path.as(String.class), value.toString());
        case LESS_THAN -> builder.lessThan(path.as(String.class), value.toString());
        case GREATER_THAN_OR_EQUAL_TO -> builder.greaterThanOrEqualTo(path.as(String.class), value.toString());
        case LESS_THAN_OR_EQUAL_TO -> builder.lessThanOrEqualTo(path.as(String.class), value.toString());
        case NOT_EQUALS -> builder.notEqual(path, value);
        case IN -> {
            CriteriaBuilder.In<Object> inClause = builder.in(path);
            if (value instanceof List) {
                ((List<?>) value).forEach(inClause::value);
            } else {
                inClause.value(value);
            }
            yield inClause;
        }
        case NOT_IN -> {
            CriteriaBuilder.In<Object> inClause = builder.in(path);
            if (value instanceof List) {
                ((List<?>) value).forEach(v -> inClause.value(v));
            } else {
                inClause.value(value);
            }
            yield builder.not(inClause);
        }
        case IS_NULL -> builder.isNull(path);
        case IS_NOT_NULL -> builder.isNotNull(path);
        case IS_TRUE -> builder.isTrue(path.as(Boolean.class));
        case IS_FALSE -> builder.isFalse(path.as(Boolean.class));
        default -> throw new IllegalArgumentException("Operación no soportada: " + criteria.getOperation());
    };
}

    private boolean isValidUUID(String str) {
        try {
            UUID uuid = UUID.fromString(str);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
