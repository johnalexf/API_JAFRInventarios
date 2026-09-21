package com.jafrinventarios.api.controller.usuarios;

import com.jafrinventarios.api.entity.usuarios.Usuario;
import com.jafrinventarios.api.service.usuarios.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> iniciarSesion(@RequestBody Map<String, String> credenciales) {
        try {
            // Extraemos los datos del JSON que envía Postman
            String correo = credenciales.get("correo");
            String contrasenaPlana = credenciales.get("contrasena");

            Usuario usuario = authService.autenticarUsuario(correo, contrasenaPlana);

            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("mensaje", "Autenticacion satisfactoria, bienvenido " + usuario.getAliasUsuario());

            return new ResponseEntity<>(respuesta, HttpStatus.OK);

        } catch (Exception e) {
            // Retorna 401 Unauthorized si las credenciales fallan
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
}