package com.kynsof.treatments.application.command.vaccine.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import com.kynsof.treatments.domain.dto.enumDto.RouteOfAdministration;
import com.kynsof.treatments.domain.dto.enumDto.VaccineType;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateVaccineCommand implements ICommand {
    private  UUID id;
    private final String name;
    private final String description;
    private final VaccineType type;
    private final double minAge;
    private final double maxAge;
    private final String dose;
    private final RouteOfAdministration routeOfAdministration;
    private final String preventableDiseases;
    private final UUID serviceId;


    public CreateVaccineCommand(String name, String description, VaccineType type, double minAge, double maxAge,
                                String dose, RouteOfAdministration routeOfAdministration, String preventableDiseases,
                                UUID serviceId){


        this.name = name;
        this.description = description;
        this.type = VaccineType.HUMAN;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.dose = dose;
        this.routeOfAdministration = routeOfAdministration;
        this.preventableDiseases = preventableDiseases;
        this.serviceId = serviceId;
    }

    public static CreateVaccineCommand fromRequest(CreateVaccineRequest request) {
        return new CreateVaccineCommand(request.getName(), request.getDescription(), request.getType(),
                request.getMinAge(), request.getMaxAge(),request.getDose(), request.getRouteOfAdministration(),
                request.getPreventableDiseases(),request.getServiceId() );
    }


    @Override
    public ICommandMessage getMessage() {
        return new CreateVaccineMessage(id);
    }
}
