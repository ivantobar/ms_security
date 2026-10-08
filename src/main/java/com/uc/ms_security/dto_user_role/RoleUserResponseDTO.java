package com.uc.ms_security.dto_user_role;

import com.uc.ms_security.dto_user.UserResponseDTO;
import lombok.Value;

@Value
public class RoleUserResponseDTO {
    Long id;
    Long roleId;
    UserResponseDTO user;
}
