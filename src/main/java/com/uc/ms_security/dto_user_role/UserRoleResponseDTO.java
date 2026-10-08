package com.uc.ms_security.dto_user_role;

import com.uc.ms_security.dto_role.RoleResponseDTO;
import lombok.Value;

@Value
public class UserRoleResponseDTO {
    Long id;
    Long userId;
    RoleResponseDTO role;
}
