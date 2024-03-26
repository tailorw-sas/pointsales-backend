package com.kynsof.identity.application.query.users.userMe;


import com.kynsof.identity.domain.dto.me.BusinessRolesPermissionsDto;
import com.kynsof.identity.domain.dto.me.UserMeDto;
import com.kynsof.share.core.domain.bus.query.IResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserMeResponse implements IResponse {
    private UUID userId;
    private String userName;
    private String email;
    private List<BusinessRolesPermissionsDto> businesses;
    public UserMeResponse(UserMeDto userMeDto) {
        this.userId = userMeDto.getUserId();
        this.userName = userMeDto.getUserName();
        this.email = userMeDto.getEmail();
        // Suponiendo que la estructura de BusinessRolesPermissionsDto en UserMeDto y UserMeResponse son compatibles
        // Puedes necesitar ajustar esta conversión dependiendo de las diferencias exactas entre las dos clases
        this.businesses = userMeDto.getBusinesses().stream()
                .map(business -> new BusinessRolesPermissionsDto(
                        business.getBusinessId(),
                        business.getName(),
                        business.getRoles(), // Este mapeo puede requerir atención adicional si las estructuras son diferentes
                        business.getUniquePermissions() // Igualmente, este mapeo puede requerir ajustes
                ))
                .collect(Collectors.toList());
    }
}