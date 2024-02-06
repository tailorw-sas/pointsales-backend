package com.kynsof.scheduled.infrastructure.entity.specifications;

import com.kynsof.scheduled.domain.dto.EStatusSchedule;
import com.kynsof.scheduled.infrastructure.entity.Schedule;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
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
public class ScheduleSpecifications implements Specification<Schedule> {
    private UUID resource;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate date;
    private EStatusSchedule status;

    public ScheduleSpecifications(UUID resource, LocalDate startDate, LocalDate endDate, LocalDate date, EStatusSchedule status) {
        this.resource = resource;
        this.startDate = startDate;
        this.endDate = endDate;
        this.date = date;
        this.status = status;
    }

    @Override
    public Predicate toPredicate(Root<Schedule> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (date != null) {
            predicates.add(cb.equal(root.get("date"), date));
        }

        if (status != null) {
            predicates.add(cb.equal(root.get("status"), status));
        }

        if (resource != null) {
            predicates.add(cb.equal(root.get("resource").get("id"), resource));
        }

        if (startDate != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("date"), startDate));
        }

        if (endDate != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("date"), endDate));
        }

        return cb.and(predicates.toArray(Predicate[]::new));
    }
}
