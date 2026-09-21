package com.jafrinventarios.api.repository.usuarios;

import com.jafrinventarios.api.entity.usuarios.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    // Spring construye la consulta SQL "SELECT * FROM Usuarios WHERE correo_usuario = ?"
    // automáticamente con solo leer el nombre de este método en cammelCase.
    Optional<Usuario> findByCorreoUsuario(String correoUsuario);

    // Funciona igual para comprobar si existe un registro y devuelve un boolean
    boolean existsByCorreoUsuario(String correoUsuario);
}