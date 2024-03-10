package com.kynsof.calendar.application.command.business.create;

import com.kynsof.share.core.application.FileRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBusinessRequest {

    private String name;
    private String latitude;
    private String longitude;
    private String description;
    private FileRequest logo;
    private String ruc;

}
