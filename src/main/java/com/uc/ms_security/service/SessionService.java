package com.uc.ms_security.service;

import com.uc.ms_security.dto_session.CreateSessionDTO;
import com.uc.ms_security.dto_session.SessionRequestDTO;
import com.uc.ms_security.dto_session.SessionResponseDTO;
import com.uc.ms_security.dto_session.UpdateSessionDTO;
import com.uc.ms_security.entity.Session;
import com.uc.ms_security.entity.User;
import com.uc.ms_security.exception.ApplicationException;
import com.uc.ms_security.exception.ErrorCase;
import com.uc.ms_security.mapper.SessionMapper;
import com.uc.ms_security.repository.SessionRepository;
import com.uc.ms_security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;
    private final SessionMapper sessionMapper;

    public SessionResponseDTO create(CreateSessionDTO dto) {
        User user = findUser(dto.getUserId());

        if (sessionRepository.existsByToken(dto.getToken())) {
            throw new ApplicationException(
                    ErrorCase.ALREADY_EXISTS,
                    "Ya existe una sesión con ese token"
            );
        }

        Session session = sessionMapper.toEntity(dto, user);
        Session savedSession = sessionRepository.save(session);
        return sessionMapper.toResponseDTO(savedSession);
    }

    public List<SessionResponseDTO> findAll() {
        List<Session> sessions = sessionRepository.findAll();
        return sessionMapper.toResponseDTOList(sessions);
    }

    public SessionResponseDTO findById(Long id) {
        Session session = findEntityById(id);
        return sessionMapper.toResponseDTO(session);
    }

    public List<SessionResponseDTO> findByUser(Long userId) {
        findUser(userId);
        List<Session> sessions = sessionRepository.findAllByUserId(userId);
        return sessionMapper.toResponseDTOList(sessions);
    }

    public SessionResponseDTO create(Long userId, SessionRequestDTO dto) {
        User user = findUser(userId);

        if (sessionRepository.existsByToken(dto.getToken())) {
            throw new ApplicationException(
                    ErrorCase.ALREADY_EXISTS,
                    "El token ya está registrado"
            );
        }

        Session session = sessionMapper.toEntity(dto);
        session.setUser(user);
        return sessionMapper.toResponseDTO(sessionRepository.save(session));
    }

    public List<SessionResponseDTO> findAllByUserId(Long userId) {
        findUser(userId);
        return sessionMapper.toResponseDTOList(
                sessionRepository.findAllByUserId(userId)
        );
    }

    public SessionResponseDTO findById(Long userId, Long sessionId) {
        return sessionMapper.toResponseDTO(findSession(userId, sessionId));
    }

    public SessionResponseDTO update(
            Long userId,
            Long sessionId,
            SessionRequestDTO dto) {

        Session session = findSession(userId, sessionId);
        if (sessionRepository.existsByTokenAndIdNot(
                dto.getToken(), sessionId)) {
            throw new ApplicationException(
                    ErrorCase.ALREADY_EXISTS,
                    "El token ya está registrado"
            );
        }

        sessionMapper.updateEntity(dto, session);
        return sessionMapper.toResponseDTO(sessionRepository.save(session));
    }

    public void delete(Long userId, Long sessionId) {
        sessionRepository.delete(findSession(userId, sessionId));
    }

    public SessionResponseDTO update(Long id, UpdateSessionDTO dto) {
        Session session = findEntityById(id);
        if (sessionRepository.existsByTokenAndIdNot(dto.getToken(), id)) {
            throw new ApplicationException(
                    ErrorCase.ALREADY_EXISTS,
                    "Ya existe otra sesión con ese token"
            );
        }
        sessionMapper.updateEntity(dto, session);
        Session updatedSession = sessionRepository.save(session);
        return sessionMapper.toResponseDTO(updatedSession);
    }

    public void delete(Long id) {
        Session session = findEntityById(id);
        sessionRepository.delete(session);
    }

    private Session findEntityById(Long id) {
        return sessionRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(
                        ErrorCase.NOT_FOUND,
                        "Sesión no encontrada con id: " + id
                ));
    }

    private User findUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ApplicationException(
                        ErrorCase.NOT_FOUND,
                        "Usuario no encontrado con id: " + userId
                ));
    }

    private Session findSession(Long userId, Long sessionId) {
        return sessionRepository.findByIdAndUserId(sessionId, userId)
                .orElseThrow(() -> new ApplicationException(
                        ErrorCase.NOT_FOUND,
                        "Sesión no encontrada para este usuario"
                ));
    }
}