package com.kynsoft.gateway.domain.dto.user;

import com.kynsof.share.core.domain.UserType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSystemRequest {
    private String userName;
    private String email;
    private String name;
    private String lastName;
    private String password;
    private UserType type;
    //List<String> roles;
}
