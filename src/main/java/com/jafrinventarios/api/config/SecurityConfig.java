package com.jafrinventarios.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    /**
     * Define el algoritmo de encriptación estándar para la aplicación.
     * Al usar @Bean, Spring instancia este BCryptPasswordEncoder una sola vez al arrancar,
     * lo guarda en su contenedor global, y lo inyecta automáticamente en los
     * constructores de los servicios que lo soliciten (como AuthService y UsuarioService).
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Deshabilita la protección CSRF para poder recibir peticiones POST desde Postman
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Autoriza esta ruta específica para que cualquiera pueda registrarse
                        .requestMatchers("/api/usuarios/registro").permitAll()
                        // Autoriza esta ruta específica para que cualquiera pueda iniciar sesion
                        .requestMatchers("/api/auth/login").permitAll()
                        // Exige autenticación para cualquier otra ruta futura
                        .anyRequest().authenticated()
                );
        return http.build();
    }
}