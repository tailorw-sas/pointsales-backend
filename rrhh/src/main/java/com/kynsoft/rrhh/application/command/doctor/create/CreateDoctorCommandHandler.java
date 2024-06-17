package com.kynsoft.rrhh.application.command.doctor.create;

import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.rules.ValidateObjectNotNullRule;
import com.kynsoft.rrhh.domain.dto.DoctorDto;
import com.kynsoft.rrhh.domain.interfaces.services.IDoctorService;
import com.kynsoft.rrhh.domain.rules.users.UserSystemEmailValidateRule;
import org.springframework.stereotype.Component;

@Component
public class CreateDoctorCommandHandler implements ICommandHandler<CreateDoctorCommand> {

    private final IDoctorService service;

    public CreateDoctorCommandHandler(IDoctorService service) {
        this.service = service;
    }

    @Override
    public void handle(CreateDoctorCommand command) {
        RulesChecker.checkRule(new UserSystemEmailValidateRule(command.getEmail()));

        //TODO yannier validar que la identificacion y el correo deben ser unico
        RulesChecker.checkRule(new ValidateObjectNotNullRule<>(command.getStatus(), "Doctor.status", "Doctor status cannot be null."));

        DoctorDto doctorSave = new DoctorDto(
                command.getId(),
                command.getIdentification(),
                command.getEmail(),
                command.getName(),
                command.getLastName(),
                command.getStatus(),
                command.getRegisterNumber(),
                command.getLanguage(),
                command.isExpress(),
                command.getPhoneNumber(),
                command.getImage()
        );

        service.create(doctorSave);
    }
}