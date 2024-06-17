package com.kynsoft.rrhh.application.command.doctor.delete;

import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsoft.rrhh.domain.dto.DoctorDto;
import com.kynsoft.rrhh.domain.interfaces.services.IDoctorService;
import org.springframework.stereotype.Component;

@Component
public class DeleteDoctorCommandHandler implements ICommandHandler<DeleteDoctorCommand> {

    private final IDoctorService service;

    public DeleteDoctorCommandHandler(IDoctorService service) {
        this.service = service;
    }

    @Override
    public void handle(DeleteDoctorCommand command) {
        DoctorDto delete = this.service.findById(command.getId());
        service.delete(delete);
    }

}
