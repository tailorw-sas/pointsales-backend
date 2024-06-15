package com.kynsof.rrhh.doman.interfaces.services;

import com.kynsof.rrhh.doman.dto.BusinessDto;

import java.util.UUID;

public interface IBusinessService {
    public void create(BusinessDto object);
    public void update(BusinessDto object);
    public void delete(UUID id);
    public BusinessDto findById(UUID id);
}