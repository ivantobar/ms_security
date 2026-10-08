package com.uc.ms_security.dto_user_role;

import lombok.Value;

import java.util.List;

@Value
public class UserRolesResponseDTO {
    Long id;
    String name;
    String email;
    List<UserRoleResponseDTO> roles;
}
