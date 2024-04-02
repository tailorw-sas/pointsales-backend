package com.kynsof.identity.application.command.business.create;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBusinessRequest {
    private String name;
    private String latitude;
    private String longitude;
    private String description;
    private  byte [] logo;
    private String ruc;
    private String address;;
    private UUID geographicLocation;

}
