package com.kynsof.calendar.application.command.serviceType.create;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateServiceTypeRequest {

    private String name;
    @NotBlank(message = "Image is required and cannot be blank")
    private  String image;

}
