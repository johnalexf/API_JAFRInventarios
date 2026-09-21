package com.jafrinventarios.api.repository.usuarios;

import com.jafrinventarios.api.entity.usuarios.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
    // Al indicarle <Rol, Integer>, Spring sabe que esta interfaz maneja la tabla Roles y que su Primary Key es un Integer.
}
