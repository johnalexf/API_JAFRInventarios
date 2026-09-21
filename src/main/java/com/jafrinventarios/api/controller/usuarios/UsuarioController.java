package com.jafrinventarios.api.controller.usuarios;

import com.jafrinventarios.api.entity.usuarios.Usuario;
import com.jafrinventarios.api.exception.ExcepcionValidacionBD;
import com.jafrinventarios.api.service.usuarios.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Controlador REST encargado de exponer los endpoints relacionados con la
 * gestión y operaciones de la entidad Usuario.
 * La anotación @RestController le indica a Spring que esta clase procesará
 * peticiones web y devolverá respuestas directamente en formato JSON.
 */
@RestController
@RequestMapping("/api/usuarios") // Define la ruta base para todos los endpoints de esta clase
public class UsuarioController {

    private final UsuarioService usuarioService;

    /**
     * Inyección de dependencias mediante constructor.
     * Spring Boot proporciona automáticamente la instancia de UsuarioService
     * al instanciar este controlador.
     *
     * @param usuarioService Servicio que contiene la lógica de negocio de usuarios.
     */
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Procesa la petición POST para registrar un nuevo usuario en el sistema.
     *
     * @param usuario Objeto mapeado automáticamente desde el cuerpo de la petición (JSON).
     *                La anotación @RequestBody es crucial para esta conversión.
     * @param idRol Identificador del rol, recibido como parámetro en la URL
     *              (ej: /api/usuarios/registro?idRol=2).
     * @return ResponseEntity con el usuario creado (HTTP 201) o un mensaje de error (HTTP 400).
     */
    @PostMapping("/registro")
    public ResponseEntity<?> registrarUsuario(@RequestBody Usuario usuario, @RequestParam Integer idRol) {
        try {
            // Delega la lógica de validación e inserción a la capa de servicio
            Usuario nuevoUsuario = usuarioService.registrarNuevoUsuario(usuario, idRol);

            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("mensaje", "Usuario creado correctamente");
            respuesta.put("usuario", nuevoUsuario);

            // Si tiene éxito, devuelve el objeto JSON y el código de estado 201 (Created)
            return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
        } catch (ExcepcionValidacionBD e) {
            // Se retorna el Map de la excepción directamente
            // Spring lo convierte a JSON: {"correo": "ya existe", "alias": "en uso"}
            return new ResponseEntity<>(e.getErrores(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            // Si el servicio lanza una excepción (ej: correo duplicado), se captura
            // y se devuelve el mensaje de error con un código 400 (Bad Request)
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}