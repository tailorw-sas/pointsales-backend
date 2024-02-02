package com.kynsof.patients.domain.service;

import com.kynsof.patients.domain.dto.PaginatedResponse;
import com.kynsof.patients.domain.dto.ContactInfoDto;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IContactInfoService {
    public UUID create(ContactInfoDto patients);
    UUID update(ContactInfoDto patients);
    public void delete(UUID id);
    public ContactInfoDto findById(UUID id);
    public PaginatedResponse findAll(Pageable pageable, UUID idPatients,  String email, String phone);
}