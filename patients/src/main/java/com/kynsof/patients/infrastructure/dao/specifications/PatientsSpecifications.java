package com.kynsof.patients.infrastructure.dao.specifications;

import com.kynsof.patients.infrastructure.dao.PatientsDAO;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class PatientsSpecifications implements Specification<PatientsDAO> {
    private UUID patients;
    private String identification;

    public PatientsSpecifications(UUID patients, String identification) {
        this.patients = patients;
        this.identification = identification;
    }

    @Override
    public Predicate toPredicate(Root<PatientsDAO> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (patients != null) {
            predicates.add(cb.equal(root.get("id"), patients));
        }

        if (StringUtils.isNotEmpty(identification)) {
            predicates.add(cb.equal(root.get("identification"), identification));
        }

        return cb.and(predicates.toArray(Predicate[]::new));
    }
}
