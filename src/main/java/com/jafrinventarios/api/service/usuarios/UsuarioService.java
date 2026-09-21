package com.jafrinventarios.api.service.usuarios;

import com.jafrinventarios.api.entity.usuarios.Rol;
import com.jafrinventarios.api.entity.usuarios.Usuario;
import com.jafrinventarios.api.repository.usuarios.RolRepository;
import com.jafrinventarios.api.repository.usuarios.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Servicio encargado de gestionar la lógica de negocio y las operaciones
 * transaccionales asociadas a la entidad Usuario.
 */
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Inyecta las dependencias requeridas para la persistencia y seguridad.
     *
     * @param usuarioRepository Repositorio para operaciones de base de datos de usuarios.
     * @param rolRepository Repositorio para operaciones de base de datos de roles.
     */
    public UsuarioService(UsuarioRepository usuarioRepository, RolRepository rolRepository) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    /**
     * Procesa el registro de un nuevo usuario en el sistema aplicando las reglas
     * de validación de negocio y el cifrado de credenciales.
     *
     * @param nuevoUsuario Objeto que contiene los datos capturados del usuario.
     * @param idRolEsperado Identificador numérico del rol que se asignará al usuario.
     * @return La entidad Usuario persistida en la base de datos con su ID autogenerado.
     * @throws Exception Si el correo electrónico ya se encuentra registrado o si el rol no existe.
     */
    public Usuario registrarNuevoUsuario(Usuario nuevoUsuario, Integer idRolEsperado) throws Exception {

        // Verifica la disponibilidad del correo electrónico en los registros existentes.
        if (usuarioRepository.existsByCorreoUsuario(nuevoUsuario.getCorreoUsuario())) {
            throw new Exception("El correo electrónico ingresado ya se encuentra registrado en el sistema.");
        }

        // Consulta el rol especificado; interrumpe el flujo si el identificador no es válido.
        Rol rol = rolRepository.findById(idRolEsperado)
                .orElseThrow(() -> new Exception("El rol especificado no se encuentra configurado en la base de datos."));

        nuevoUsuario.setRol(rol);

        // Aplica el algoritmo de hashing BCrypt para proteger la contraseña antes de la persistencia.
        String hash = passwordEncoder.encode(nuevoUsuario.getContrasenaUsuario());
        nuevoUsuario.setContrasenaUsuario(hash);

        // Retorna el objeto guardado exitosamente.
        return usuarioRepository.save(nuevoUsuario);
    }
}