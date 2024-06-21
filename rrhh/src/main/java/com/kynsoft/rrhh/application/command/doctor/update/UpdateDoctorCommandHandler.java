package com.kynsoft.rrhh.application.command.doctor.update;

import com.kynsof.share.core.domain.RulesChecker;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.core.domain.rules.ValidateObjectNotNullRule;
import com.kynsof.share.utils.ConsumerUpdate;
import com.kynsof.share.utils.UpdateIfNotNull;
import com.kynsoft.rrhh.domain.dto.DoctorDto;
import com.kynsoft.rrhh.domain.interfaces.services.IDoctorService;
import com.kynsoft.rrhh.domain.rules.doctor.UpdateDoctorEmailMustBeUniqueRule;
import com.kynsoft.rrhh.domain.rules.doctor.UpdateDoctorIdentificationMustBeUniqueRule;
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
        ConsumerUpdate update = new ConsumerUpdate();

        if (UpdateIfNotNull.updateIfStringNotNullNotEmptyAndNotEquals(doctorSave::setEmail, command.getEmail(), doctorSave.getEmail(), update::setUpdate)) {
            RulesChecker.checkRule(new UpdateDoctorEmailMustBeUniqueRule(this.service, command.getEmail(), command.getId()));
        }
        if (UpdateIfNotNull.updateIfStringNotNullNotEmptyAndNotEquals(doctorSave::setIdentification, command.getIdentification(), doctorSave.getEmail(), update::setUpdate)) {
            RulesChecker.checkRule(new UpdateDoctorIdentificationMustBeUniqueRule(this.service, command.getIdentification(), command.getId()));
        }
        UpdateIfNotNull.updateIfStringNotNullNotEmptyAndNotEquals(doctorSave::setEmail, command.getEmail(), doctorSave.getEmail(), update::setUpdate);
        UpdateIfNotNull.updateIfStringNotNullNotEmptyAndNotEquals(doctorSave::setIdentification, command.getIdentification(), doctorSave.getEmail(), update::setUpdate);
        UpdateIfNotNull.updateIfStringNotNullNotEmptyAndNotEquals(doctorSave::setRegisterNumber, command.getRegisterNumber(), doctorSave.getRegisterNumber(), update::setUpdate);
        UpdateIfNotNull.updateIfStringNotNullNotEmptyAndNotEquals(doctorSave::setLanguage, command.getLanguage(), doctorSave.getLanguage(), update::setUpdate);
        UpdateIfNotNull.updateIfStringNotNullNotEmptyAndNotEquals(doctorSave::setName, command.getName(), doctorSave.getName(), update::setUpdate);
        UpdateIfNotNull.updateIfStringNotNullNotEmptyAndNotEquals(doctorSave::setLastName, command.getLastName(), doctorSave.getLastName(), update::setUpdate);
        UpdateIfNotNull.updateIfStringNotNullNotEmptyAndNotEquals(doctorSave::setPhoneNumber, command.getPhoneNumber(), doctorSave.getPhoneNumber(), update::setUpdate);
        UpdateIfNotNull.updateIfStringNotNullNotEmptyAndNotEquals(doctorSave::setImage, command.getImage(), doctorSave.getImage(), update::setUpdate);
        doctorSave.setStatus(command.getStatus());
        doctorSave.setExpress(command.isExpress());

        service.update(doctorSave);
    }
}
