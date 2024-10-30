package com.kynsoft.notification.application.query.file.search;

import com.kynsof.share.core.domain.bus.query.IResponse;
import com.kynsoft.notification.domain.dto.AFileDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class FileResponse implements IResponse {
    private UUID id;
    private String name;
    private String url;

    public FileResponse(AFileDto fileDto) {
        this.id = fileDto.getId();
        this.name = fileDto.getName();
        this.url = fileDto.getUrl();
    }

}