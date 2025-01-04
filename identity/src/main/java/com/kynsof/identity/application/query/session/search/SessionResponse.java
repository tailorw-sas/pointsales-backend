package com.kynsof.identity.application.query.session.search;

import com.kynsof.identity.domain.dto.SessionDto;
import com.kynsof.identity.domain.dto.enumType.ESessionStatus;
import com.kynsof.identity.application.query.users.getSearch.UserSystemsResponse;
import com.kynsof.identity.application.query.business.search.BusinessResponse;
import com.kynsof.share.core.domain.bus.query.IResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class SessionResponse implements IResponse {
    private UUID id;
    private UserSystemsResponse userSystem;
    private ESessionStatus status;
    private BusinessResponse businessResponse;
    private LocalDate createdAt;

    public SessionResponse(SessionDto object) {
        this.id = object.getId();
        this.userSystem = object.getBusinessDto() != null ?
                new UserSystemsResponse(object.getUserSystemDto()) : null;
        this.status = object.getStatus();
        this.businessResponse = object.getBusinessDto() != null ?
                new BusinessResponse(object.getBusinessDto()) : null;
        this.createdAt = object.getCreateAt().toLocalDate();
    }
}
