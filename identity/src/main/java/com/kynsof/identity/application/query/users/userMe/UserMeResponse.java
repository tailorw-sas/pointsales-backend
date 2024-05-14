package com.kynsof.identity.application.query.users.userMe;


import com.kynsof.identity.domain.dto.me.PermissionInfo;
import com.kynsof.identity.domain.dto.me.UserMeDto;
import com.kynsof.share.core.domain.bus.query.IResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class UserMeResponse implements IResponse {
    private UUID userId;
    private String userName;
    private String email;
    private String name;
    private String lastName;
    private String image;
    private UUID selectedBusiness;
    private List<BusinessPermissionResponse> businesses;

    public UserMeResponse(UserMeDto userMeDto) {
        this.userId = userMeDto.getUserId();
        this.userName = userMeDto.getUserName();
        this.email = userMeDto.getEmail();
        this.name = userMeDto.getName();
        this.lastName = userMeDto.getLastName();
        this.image = userMeDto.getImage();
        this.businesses = userMeDto.getBusiness().stream().map(b -> {
            List<String> permissions = b.getUniquePermissions().stream().map(PermissionInfo::getCode).toList();
            return new BusinessPermissionResponse(b.getBusinessId(),b.getName(),permissions);
        }).toList();

        this.selectedBusiness = userMeDto.getSelectedBusiness() == null ? this.businesses.get(0).getBusinessId() : userMeDto.getSelectedBusiness();
    }
}