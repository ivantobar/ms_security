package com.uc.ms_security.repository;

import com.uc.ms_security.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, Long id);

    @EntityGraph(attributePaths = {"profile"})
    Optional<User> findWithProfileById(Long id);

    @EntityGraph(attributePaths = {"sessions"})
    Optional<User> findWithSessionsById(Long id);

    @EntityGraph(attributePaths = {"userRoles", "userRoles.role"}) 
    Optional<User> findWithRolesById(Long id);// esto es un join triple, porque se hace un join con user_roles y luego con role, para traer los roles del usuario. Esto es para cuando se quiere traer los roles de un usuario, para poder mostrarlos en la respuesta.
}
