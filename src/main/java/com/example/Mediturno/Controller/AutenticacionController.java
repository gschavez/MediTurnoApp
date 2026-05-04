package com.example.Mediturno.Controller;

import com.example.Mediturno.DTO.RespuestaJwt;
import com.example.Mediturno.DTO.SolicitudLogin;
import com.example.Mediturno.DTO.SolicitudRegistro;
import com.example.Mediturno.Service.AutenticacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AutenticacionController {

    private final AutenticacionService autenticacionService;

    @PostMapping("/login")
    public ResponseEntity<RespuestaJwt> login(@Valid @RequestBody SolicitudLogin solicitud) {
        return ResponseEntity.ok(autenticacionService.autenticar(solicitud));
    }

    @PostMapping("/registro")
    public ResponseEntity<String> registro(@Valid @RequestBody SolicitudRegistro solicitud) {
        autenticacionService.registrarUsuario(solicitud);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Usuario registrado exitosamente");
    }
}