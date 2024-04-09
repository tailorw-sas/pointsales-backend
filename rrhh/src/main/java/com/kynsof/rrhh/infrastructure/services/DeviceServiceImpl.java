package com.kynsof.rrhh.infrastructure.services;

import com.kynsof.rrhh.application.query.device.getbyid.DeviceResponse;
import com.kynsof.rrhh.doman.dto.DeviceDto;
import com.kynsof.rrhh.doman.interfaces.services.IDeviceService;
import com.kynsof.rrhh.infrastructure.identity.Device;
import com.kynsof.rrhh.infrastructure.repository.query.DeviceReadDataJPARepository;
import com.kynsof.rrhh.infrastructure.repository.query.command.DeviceWriteDataJPARepository;
import com.kynsof.share.core.domain.exception.BusinessNotFoundException;
import com.kynsof.share.core.domain.exception.DomainErrorMessage;
import com.kynsof.share.core.domain.exception.GlobalBusinessException;
import com.kynsof.share.core.domain.response.ErrorField;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public DeviceDto findById(UUID id) {
        
        Optional<Device> object = this.repositoryQuery.findById(id);
        if (object.isPresent()) {
            return object.get().toAggregate();
        }

        throw new BusinessNotFoundException(new GlobalBusinessException(DomainErrorMessage.DEVICE_NOT_FOUND, new ErrorField("Device.id", "Device not found.")));

    }

}
