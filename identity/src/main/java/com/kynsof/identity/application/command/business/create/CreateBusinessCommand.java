package com.kynsof.identity.application.command.business.create;

import com.kynsof.share.core.domain.bus.command.ICommand;
import com.kynsof.share.core.domain.bus.command.ICommandMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateBusinessCommand implements ICommand {

    private UUID id;
    private String name;
    private String latitude;
    private String longitude;
    private String description;
    private  byte [] logo;
    private String ruc;
    private String address;
    private UUID geographicLocation;

    public CreateBusinessCommand(String name, String latitude, String longitude, String description,  byte [] logo, String ruc, UUID geographicLocation, String address) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.logo = logo;
        this.ruc = ruc;
        this.geographicLocation = geographicLocation;
        this.address = address;
    }

    public static CreateBusinessCommand fromRequest(CreateBusinessRequest request) {
        return new CreateBusinessCommand(
                request.getName(), 
                request.getLatitude(), 
                request.getLongitude(), 
                request.getDescription(), 
                request.getImage(),
                request.getRuc(), 
                request.getGeographicLocation(),
                request.getAddress()
        );
    }

    @Override
    public ICommandMessage getMessage() {
        return new CreateBusinessMessage(id);
    }
}
