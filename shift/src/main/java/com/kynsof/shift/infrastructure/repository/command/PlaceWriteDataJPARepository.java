package com.kynsof.shift.infrastructure.repository.command;

import com.kynsof.shift.infrastructure.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PlaceWriteDataJPARepository extends JpaRepository<Place, UUID> {
}
