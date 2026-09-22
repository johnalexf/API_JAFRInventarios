package com.jafrinventarios.api.service.usuarios;

import com.jafrinventarios.api.entity.usuarios.Usuario;
import com.jafrinventarios.api.repository.usuarios.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario autenticarUsuario(String credencial, String contrasenaPlana) throws Exception {

        Optional<Usuario> usuario;

        usuario = usuarioRepository.findByCorreoUsuario( credencial );

        if(usuario.isEmpty()) {
            usuario = usuarioRepository.findByAliasUsuario( credencial );
            if (usuario.isEmpty())
                throw new Exception("Credenciales invalidas");

        }

        if( !passwordEncoder.matches( contrasenaPlana, usuario.get().getContrasenaUsuario() ) )
            throw new Exception("Credenciales invalidas");

        return usuario.get();
    }
}