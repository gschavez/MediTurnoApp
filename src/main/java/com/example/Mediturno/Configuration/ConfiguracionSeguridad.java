package com.example.Mediturno.Configuration;

import com.example.Mediturno.Seguridad.FiltroAutenticacionJwt;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class ConfiguracionSeguridad {
    private final FiltroAutenticacionJwt filtroAutenticacionJwt;

    public ConfiguracionSeguridad(FiltroAutenticacionJwt filtroAutenticacionJwt) {
        this.filtroAutenticacionJwt = filtroAutenticacionJwt;
    }

    @Bean
    public SecurityFilterChain cadenaFiltros(HttpSecurity http) throws Exception {
        http
                // Deshabilitar CSRF (no necesario para APIs REST sin estado)
                .csrf(csrf -> csrf.disable())

                // Establecer la política de sesiones como STATELESS (JWT)
                .sessionManagement(sesion ->
                        sesion.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Configurar las reglas de autorización
                .authorizeHttpRequests(autorizacion -> autorizacion
                        // Endpoints públicos (sin autenticación)
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()

                        // Accesos por rol (se complementa con @PreAuthorize en controladores)
                        .requestMatchers("/api/pacientes/**").hasAnyRole("PACIENTE", "ADMINISTRADOR", "RECEPCIONISTA")
                        .requestMatchers("/api/medicos/**").hasAnyRole("MEDICO", "ADMINISTRADOR")
                        .requestMatchers("/api/especialidades/**").authenticated()
                        .requestMatchers("/api/turnos/**").authenticated()
                        .requestMatchers("/api/admin/**").hasRole("ADMINISTRADOR")

                        // Cualquier otra petición requiere autenticación
                        .anyRequest().authenticated()
                );

        // Agregar el filtro JWT antes del filtro estándar de autenticación
        http.addFilterBefore(filtroAutenticacionJwt, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * Bean para codificar contraseñas con BCrypt.
     */
    @Bean
    public PasswordEncoder codificadorContrasena() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Expone el AuthenticationManager como bean para poder inyectarlo en el servicio de autenticación.
     */
    @Bean
    public AuthenticationManager administradorAutenticacion(AuthenticationConfiguration configuracion)
            throws Exception {
        return configuracion.getAuthenticationManager();
    }
}
