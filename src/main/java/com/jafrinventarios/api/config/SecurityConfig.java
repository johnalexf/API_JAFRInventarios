package com.jafrinventarios.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Deshabilita la protección CSRF para poder recibir peticiones POST desde Postman
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Autoriza esta ruta específica para que cualquiera pueda registrarse
                        .requestMatchers("/api/usuarios/registro").permitAll()
                        // Exige autenticación para cualquier otra ruta futura
                        .anyRequest().authenticated()
                );
        return http.build();
    }
}