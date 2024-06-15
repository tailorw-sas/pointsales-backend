package com.kynsof.share.core.infrastructure.specifications;

import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.UUID;

public class GenericSpecification<T> implements Specification<T> {
    private final SearchCriteria criteria;

    public GenericSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        Path<Object> path = getPath(root);

        Object value = parseValue(criteria.getValue());

        return switch (criteria.getOperation()) {
            case LIKE -> builder.like(builder.lower(path.as(String.class)), "%" + value.toString().toLowerCase() + "%");
            case EQUALS -> builder.equal(path, value);
            case GREATER_THAN -> builder.greaterThan(path.as(Comparable.class), (Comparable) value);
            case LESS_THAN -> builder.lessThan(path.as(Comparable.class), (Comparable) value);
            case GREATER_THAN_OR_EQUAL_TO -> builder.greaterThanOrEqualTo(path.as(Comparable.class), (Comparable) value);
            case LESS_THAN_OR_EQUAL_TO -> builder.lessThanOrEqualTo(path.as(Comparable.class), (Comparable) value);
            case NOT_EQUALS -> builder.notEqual(path, value);
            case IN -> builder.in(path).value(value);
            case NOT_IN -> builder.not(builder.in(path).value(value));
            case IS_NULL -> builder.isNull(path);
            case IS_NOT_NULL -> builder.isNotNull(path);
            case IS_TRUE -> builder.isTrue(path.as(Boolean.class));
            case IS_FALSE -> builder.isFalse(path.as(Boolean.class));
            default -> throw new IllegalArgumentException("Operación no soportada: " + criteria.getOperation());
        };
    }

    private Path<Object> getPath(Root<T> root) {
        String[] keys = criteria.getKey().split("\\.");
        Path<Object> path = root.get(keys[0]);

        for (int i = 1; i < keys.length; i++) {
            path = path.get(keys[i]);
        }

        return path;
    }

    private Object parseValue(Object value) {
        if (value instanceof String && isValidUUID((String) value)) {
            return UUID.fromString((String) value);
        }

        try {
            return LocalDate.parse(value.toString(), DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException ignored) {
            try {
                return LocalDateTime.parse(value.toString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            } catch (DateTimeParseException ignored2) {
                return value;
            }
        }
    }

    private boolean isValidUUID(String str) {
        try {
            UUID.fromString(str);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}