package com.kynsof.scheduled.infrastructure.entity.specifications;

import com.kynsof.scheduled.infrastructure.entity.GeographicLocation;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GeographicLocationSpecifications implements Specification<GeographicLocation> {
    private final String name;
    private final UUID parentId;
    private final String type;

    public GeographicLocationSpecifications(String name, UUID parentId, String type) {
        this.parentId = parentId;
        this.name = name;
        this.type = type;
    }

    @Override
    public Predicate toPredicate(Root<GeographicLocation> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (parentId != null) {
            predicates.add(cb.equal(root.join("parent").get("id"), parentId));
        }

        if (StringUtils.isNotEmpty(name)) {
            predicates.add(cb.equal(root.get("name"), name));
        }
        if (StringUtils.isNotEmpty(type)) {
            predicates.add(cb.equal(root.get("type"), type));
        }


        return cb.and(predicates.toArray(Predicate[]::new));
    }
}
