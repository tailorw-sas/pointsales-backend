package com.kynsoft.rrhh.application.command.doctor.update;

import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.rules.ValidateObjectNotNullRule;
import com.kynsoft.rrhh.domain.dto.DoctorDto;
import com.kynsoft.rrhh.domain.interfaces.services.IDoctorService;
import com.kynsoft.rrhh.domain.rules.users.UserSystemEmailValidateRule;
import org.springframework.stereotype.Component;

@Component
public class UpdateDoctorCommandHandler implements ICommandHandler<UpdateDoctorCommand> {

    private final IDoctorService service;

    public UpdateDoctorCommandHandler(IDoctorService service) {
        this.service = service;
    }

    @Override
    public void handle(UpdateDoctorCommand command) {
        RulesChecker.checkRule(new UserSystemEmailValidateRule(command.getEmail()));

        //TODO yannier validar que la identificacion y el correo deben ser unico
        RulesChecker.checkRule(new ValidateObjectNotNullRule<>(command.getStatus(), "Doctor.status", "Doctor status cannot be null."));

        DoctorDto doctorSave = service.findById(command.getId());
        doctorSave.setEmail(command.getEmail());
        doctorSave.setStatus(command.getStatus());
        doctorSave.setExpress(command.isExpress());
        doctorSave.setRegisterNumber(command.getRegisterNumber());
        doctorSave.setLanguage(command.getLanguage());
        doctorSave.setIdentification(command.getIdentification());
        doctorSave.setName(command.getName());
        doctorSave.setLastName(command.getLastName());
        doctorSave.setPhoneNumber(command.getPhoneNumber());
        doctorSave.setImage(command.getImage());

        service.update(doctorSave);
    }
}