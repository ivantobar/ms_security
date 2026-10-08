package com.uc.ms_security.dto_user;

import com.uc.ms_security.dto_session.SessionResponseDTO;
import lombok.Value;

import java.util.List;

@Value
public class UserSessionsResponseDTO {
    Long id;
    String name;
    String email;
    List<SessionResponseDTO> sessions;
}
