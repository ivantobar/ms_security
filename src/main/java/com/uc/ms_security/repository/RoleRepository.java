package com.uc.ms_security.repository;

import com.uc.ms_security.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    boolean existsByNameIgnoreCase(String name);

    boolean existsByNameIgnoreCaseAndIdNot(String name, Long id);//que hace esto? sirve para validar que no exista otro rol con el mismo nombre, pero que no sea el mismo rol que se esta editando. Esto es para cuando se edita un rol, no se pueda poner el mismo nombre que otro rol ya existente.
}