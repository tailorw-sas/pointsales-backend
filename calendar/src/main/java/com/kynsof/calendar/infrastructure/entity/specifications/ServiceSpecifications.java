package com.kynsof.calendar.infrastructure.entity.specifications;

import com.kynsof.calendar.infrastructure.entity.Services;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ServiceSpecifications implements Specification<Services> {
    private UUID idObject;
    private String filter;

    public ServiceSpecifications(UUID idObject, String filter) {
        this.idObject = idObject;
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<Services> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (idObject != null) {
            predicates.add(cb.equal(root.get("id"), idObject));
        }

        return cb.and(predicates.toArray(Predicate[]::new));
    }
}
