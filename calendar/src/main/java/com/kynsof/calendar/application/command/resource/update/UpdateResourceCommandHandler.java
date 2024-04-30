package com.kynsof.calendar.application.command.resource.update;

import com.kynsof.calendar.domain.dto.ResourceDto;
import com.kynsof.calendar.domain.service.IResourceService;
import com.kynsof.share.core.domain.bus.command.ICommandHandler;
import com.kynsof.share.utils.UpdateIfNotNull;
import org.springframework.stereotype.Component;

@Component
public class UpdateResourceCommandHandler implements ICommandHandler<UpdateResourceCommand> {

    private final IResourceService service;


    public UpdateResourceCommandHandler(IResourceService service) {
        this.service = service;

    }

    @Override
    public void handle(UpdateResourceCommand command) {

        try {
            ResourceDto _resource = service.findById(command.getId());

            _resource.setImage(command.getPicture());
            _resource.setExpressAppointments(command.getExpressAppointments());
            _resource.setStatus(command.getStatus());

            UpdateIfNotNull.updateIfStringNotNull(_resource::setRegistrationNumber, command.getRegistrationNumber());
            UpdateIfNotNull.updateIfStringNotNull(_resource::setLanguage, command.getLanguage());
            UpdateIfNotNull.updateIfStringNotNull(_resource::setIdentification, command.getIdentification());
            service.update(_resource);
        } catch (Exception ex) {

            ResourceDto _resource = new ResourceDto(
                    command.getId(), 
                    "", 
                    command.getRegistrationNumber(), 
                    command.getLanguage(),
                    command.getStatus(), 
                    command.getExpressAppointments(),
                    command.getPicture()
            );
            _resource.setIdentification(command.getIdentification());
            service.create(_resource);
        }

    }
}
