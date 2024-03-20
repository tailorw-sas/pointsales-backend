package com.kynsof.identity.infrastructure.services;

import com.kynsof.identity.application.query.permission.getById.PermissionrResponse;
import com.kynsof.identity.domain.dto.PermissionDto;
import com.kynsof.identity.domain.dto.enumType.PermissionStatusEnm;
import com.kynsof.identity.domain.interfaces.service.IPermissionService;
import com.kynsof.identity.infrastructure.identity.Permission;
import com.kynsof.identity.infrastructure.repository.command.PermissionWriteDataJPARepository;
import com.kynsof.identity.infrastructure.repository.query.PermissionReadDataJPARepository;
import com.kynsof.share.core.domain.exception.BusinessException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.PaginatedResponse;
import com.kynsof.share.core.infrastructure.specifications.GenericSpecificationsBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private PermissionWriteDataJPARepository writeRepository;

    @Autowired
    private PermissionReadDataJPARepository queryRepository;

    @Override
    public UUID create(PermissionDto dto) {
        dto.setStatus(PermissionStatusEnm.ACTIVE);
        dto.setDeleted(false);
        this.writeRepository.save(new Permission(dto));
        return dto.getId();
    }

    @Override
    public void update(PermissionDto objectDto) {
        if (objectDto.getId() == null) {
            throw new BusinessException(DomainErrorMessage.PERMISSION_OR_ID_NULL, "Permission DTO or ID cannot be null.");
        }

        this.queryRepository.findById(objectDto.getId())
                .map(object -> {
                    if (objectDto.getDescription() != null) {
                        object.setDescription(objectDto.getDescription());
                    }
                    if (objectDto.getStatus() != null) {
                        object.setStatus(objectDto.getStatus());
                    }
                    if (objectDto.getCode() != null) {
                        object.setCode(objectDto.getCode());
                    }
                    return this.queryRepository.save(object);
                })
                .orElseThrow(() -> new BusinessException(DomainErrorMessage.PERMISSION_NOT_FOUND, "Permission not found."));

    }

    @Override
    public void delete(UUID id) {
        PermissionDto objectDelete = this.findById(id);
        objectDelete.setStatus(PermissionStatusEnm.INACTIVE);
        objectDelete.setDeleted(true);
        this.writeRepository.save(new Permission(objectDelete));
    }

    @Override
    public PermissionDto findById(UUID id) {
        Optional<Permission> object = this.queryRepository.findById(id);
        if (object.isPresent()) {
            return object.get().toAggregate();
        }

        throw new BusinessException(DomainErrorMessage.PERMISSION_NOT_FOUND, "Permission not found.");
    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        filterCreteria(filterCriteria);
        GenericSpecificationsBuilder<Permission> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<Permission> data = this.queryRepository.findAll(specifications, pageable);
        return getPaginatedResponse(data);
    }

    private void filterCreteria(List<FilterCriteria> filterCriteria) {
        for (FilterCriteria filter : filterCriteria) {
            if ("status".equals(filter.getKey()) && filter.getValue() instanceof String) {
                try {
                    PermissionStatusEnm enumValue = PermissionStatusEnm.valueOf((String) filter.getValue());
                    filter.setValue(enumValue);
                } catch (IllegalArgumentException e) {
                    System.err.println("Valor inválido para el tipo Enum Permisos: " + filter.getValue());
                }
            }
        }
    }

    private PaginatedResponse getPaginatedResponse(Page<Permission> data) {
        List<PermissionrResponse> patients = new ArrayList<>();
        for (Permission o : data.getContent()) {
            patients.add(new PermissionrResponse(o.toAggregate()));
        }
        return new PaginatedResponse(patients, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }

}
