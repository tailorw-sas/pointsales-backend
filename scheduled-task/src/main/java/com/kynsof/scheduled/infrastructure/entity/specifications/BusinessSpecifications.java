package com.kynsof.scheduled.infrastructure.entity.specifications;

import com.kynsof.scheduled.infrastructure.entity.Business;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.domain.Specification;

public class BusinessSpecifications implements Specification<Business> {
    private UUID idObject;
    private String filter;

    public BusinessSpecifications(UUID idObject, String filter) {
        this.idObject = idObject;
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<Business> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (idObject != null) {
            predicates.add(cb.equal(root.get("id"), idObject));
        }

        return cb.and(predicates.toArray(Predicate[]::new));
    }
}
