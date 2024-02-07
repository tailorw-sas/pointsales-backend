package com.kynsof.scheduled.infrastructure.entity.specifications;

import com.kynsof.scheduled.domain.dto.EStatusReceipt;
import com.kynsof.scheduled.infrastructure.entity.Patient;
import com.kynsof.scheduled.infrastructure.entity.Receipt;
import com.kynsof.scheduled.infrastructure.entity.Schedule;
import com.kynsof.scheduled.infrastructure.entity.Services;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

@Getter
@Setter
public class ReceiptSpecifications implements Specification<Receipt> {

    private UUID user;
    private UUID schedule;
    private UUID service;
    private UUID resource;
    private UUID receipt;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate date;
    private EStatusReceipt status;

    private String filter;

    public ReceiptSpecifications(String filter, UUID receipt, UUID user, UUID schedule, UUID service, UUID resource, LocalDate startDate, LocalDate endDate, LocalDate date, EStatusReceipt status) {
        this.receipt = receipt;
        this.user = user;
        this.schedule = schedule;
        this.service = service;
        this.resource = resource;
        this.startDate = startDate;
        this.endDate = endDate;
        this.date = date;
        this.status = status;
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<Receipt> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (date != null) {
            predicates.add(cb.equal(root.get("schedule").get("date"), date));
        }

        if (status != null) {
            predicates.add(cb.equal(root.get("status"), status));
        }

        if (resource != null) {
            predicates.add(cb.equal(root.get("schedule").get("resource").get("id"), resource));
        }

        if (schedule != null) {
            predicates.add(cb.equal(root.get("schedule").get("id"), schedule));
        }

        if (receipt != null) {
            predicates.add(cb.equal(root.get("id"), receipt));
        }

        if (service != null) {
            predicates.add(cb.equal(root.get("service").get("id"), service));
        }

        if (user != null) {
            predicates.add(cb.equal(root.get("user").get("id"), user));
        }

        if (startDate != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("schedule").get("date"), startDate));
        }

        if (endDate != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("schedule").get("date"), endDate));
        }

        if (filter != null) {
            Join<Receipt, Patient> joinUser = root.join("user");
            Join<Receipt, Schedule> joinSchedule = root.join("schedule");
            Join<Receipt, Services> joinService = root.join("service");
            predicates.add(cb.or(
                    cb.like(joinService.get("name"), "%" + filter + "%"),
                    cb.like(joinService.get("description"), "%" + filter + "%"),
                    cb.like(joinSchedule.get("resource").get("name"), "%" + filter + "%"),
                    cb.like(joinSchedule.get("resource").get("registrationNumber"), "%" + filter + "%"),
                    cb.like(joinSchedule.get("resource").get("language"), "%" + filter + "%"),
                    cb.like(root.get("reasons"), "%" + filter + "%")
            ));
        }
        return cb.and(predicates.toArray(Predicate[]::new));
    }
}
