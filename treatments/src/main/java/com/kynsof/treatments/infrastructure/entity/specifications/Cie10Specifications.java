package com.kynsof.treatments.infrastructure.entity.specifications;

import com.kynsof.treatments.infrastructure.entity.Cie10;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class Cie10Specifications implements Specification<Cie10> {
    private final String code;
    private final String name;

    public Cie10Specifications(String name, String code) {
        this.code = name;
        this.name = code;
    }

    @Override
    public Predicate toPredicate(Root<Cie10> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.isNotEmpty(name)) {
            predicates.add(cb.equal(root.get("name"), name));
        }

        if (StringUtils.isNotEmpty(code)) {
            predicates.add(cb.equal(root.get("code"), code));
        }

        return cb.and(predicates.toArray(Predicate[]::new));
    }
}
