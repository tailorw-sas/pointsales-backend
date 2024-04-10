package com.kynsof.rrhh.infrastructure.services;

import com.kynsof.rrhh.application.query.device.getbyid.DeviceResponse;
import com.kynsof.rrhh.application.query.users.getbyid.UserSystemsByIdResponse;
import com.kynsof.rrhh.doman.dto.DeviceDto;
import com.kynsof.rrhh.doman.interfaces.services.IDeviceService;
import com.kynsof.rrhh.infrastructure.identity.Device;
import com.kynsof.rrhh.infrastructure.identity.UserSystem;
import com.kynsof.rrhh.infrastructure.repository.query.DeviceReadDataJPARepository;
import com.kynsof.rrhh.infrastructure.repository.query.command.DeviceWriteDataJPARepository;
import com.kynsof.share.core.domain.exception.BusinessNotFoundException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.exception.GlobalBusinessException;
import com.kynsof.share.core.domain.request.FilterCriteria;
import com.kynsof.share.core.domain.response.ErrorField;
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
public class DeviceServiceImpl implements IDeviceService {

    @Autowired
    private DeviceWriteDataJPARepository repositoryCommand;

    @Autowired
    private DeviceReadDataJPARepository repositoryQuery;

    @Override
    public void create(DeviceDto object) {
        this.repositoryCommand.save(new Device(object));
    }

    @Override
    public void update(DeviceDto object) {
        this.repositoryCommand.save(new Device(object));
    }

    @Override
    public void delete(DeviceDto object) {
        this.repositoryCommand.save(new Device(object));
    }

    @Override
    public DeviceDto findById(UUID id) {
        
        Optional<Device> object = this.repositoryQuery.findById(id);
        if (object.isPresent()) {
            return object.get().toAggregate();
        }

        throw new BusinessNotFoundException(new GlobalBusinessException(DomainErrorMessage.DEVICE_NOT_FOUND, new ErrorField("Device.id", "Device not found.")));

    }

    @Override
    public PaginatedResponse search(Pageable pageable, List<FilterCriteria> filterCriteria) {
        GenericSpecificationsBuilder<Device> specifications = new GenericSpecificationsBuilder<>(filterCriteria);
        Page<Device> data = this.repositoryQuery.findAll(specifications, pageable);
        return getPaginatedResponse(data);
    }

    private PaginatedResponse getPaginatedResponse(Page<Device> data) {
        List<DeviceResponse> patients = new ArrayList<>();
        for (Device o : data.getContent()) {
            patients.add(new DeviceResponse(o.toAggregate()));
        }
        return new PaginatedResponse(patients, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }

    private PaginatedResponse getPaginatedResponseUserSystem(Page<UserSystem> data) {
        List<UserSystemsByIdResponse> users = new ArrayList<>();
        for (UserSystem o : data.getContent()) {
            users.add(new UserSystemsByIdResponse(o.toAggregate()));
        }
        return new PaginatedResponse(users, data.getTotalPages(), data.getNumberOfElements(),
                data.getTotalElements(), data.getSize(), data.getNumber());
    }

    @Override
    public PaginatedResponse findUsersByDeviceId(UUID deviceId, Pageable pageable) {
        Page<UserSystem> data = this.repositoryQuery.findUsersByDeviceId(deviceId, pageable);
        return getPaginatedResponseUserSystem(data);
    }

}