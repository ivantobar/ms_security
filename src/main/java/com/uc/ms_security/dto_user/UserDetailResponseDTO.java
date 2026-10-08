package com.uc.ms_security.dto_user;

import com.uc.ms_security.dto_profile.ProfileResponseDTO;
import lombok.Value;

@Value
public class UserDetailResponseDTO {
    Long id;
    String name;
    String email;
    ProfileResponseDTO profile;
}
