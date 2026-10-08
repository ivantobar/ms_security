package com.uc.ms_security.mapper;

import com.uc.ms_security.dto_session.CreateSessionDTO;
import com.uc.ms_security.dto_session.SessionRequestDTO;
import com.uc.ms_security.dto_session.SessionResponseDTO;
import com.uc.ms_security.dto_session.UpdateSessionDTO;
import com.uc.ms_security.entity.Session;
import com.uc.ms_security.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SessionMapper {

    public Session toEntity(CreateSessionDTO dto, User user) {
        Session session = new Session();
        session.setToken(dto.getToken());
        session.setExpiration(dto.getExpiration());
        session.setCode2FA(dto.getCode2FA());
        session.setUser(user);
        return session;
    }

    public Session toEntity(SessionRequestDTO dto) {
        Session session = new Session();
        session.setToken(dto.getToken());
        session.setExpiration(dto.getExpiration());
        session.setCode2FA(dto.getCode2FA());
        return session;
    }

    public void updateEntity(SessionRequestDTO dto, Session session) {
        session.setToken(dto.getToken());
        session.setExpiration(dto.getExpiration());
        session.setCode2FA(dto.getCode2FA());
    }

    public void updateEntity(UpdateSessionDTO dto, Session session) {
        session.setToken(dto.getToken());
        session.setExpiration(dto.getExpiration());

        if (dto.getCode2FA() != null) {
            session.setCode2FA(dto.getCode2FA());
        }
    }

    public SessionResponseDTO toResponseDTO(Session session) {
        return new SessionResponseDTO(
                session.getId(),
                session.getToken(),
                session.getExpiration(),
                session.getCode2FA()
        );
    }

    public List<SessionResponseDTO> toResponseDTOList(List<Session> sessions) {
        return sessions.stream()
                .map(this::toResponseDTO)
                .toList();
    }
}