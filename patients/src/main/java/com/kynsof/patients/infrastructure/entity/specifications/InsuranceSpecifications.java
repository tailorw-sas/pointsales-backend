package com.kynsof.patients.infrastructure.entity.specifications;

import com.kynsof.patients.infrastructure.entity.Insurance;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class InsuranceSpecifications implements Specification<Insurance> {
    private final String name;

    public InsuranceSpecifications( String name) {
        this.name = name;
    }

    @Override
    public Predicate toPredicate(Root<Insurance> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();


        if (StringUtils.isNotEmpty(name)) {
            predicates.add(cb.equal(root.get("name"), name));
        }

        return cb.and(predicates.toArray(Predicate[]::new));
    }
}
