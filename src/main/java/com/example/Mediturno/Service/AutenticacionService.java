package com.example.Mediturno.Service;

import com.example.Mediturno.DTO.RespuestaJwt;
import com.example.Mediturno.DTO.SolicitudLogin;
import com.example.Mediturno.DTO.SolicitudRegistro;

public interface AutenticacionService {
    RespuestaJwt autenticar(SolicitudLogin solicitud);
    void registrarUsuario(SolicitudRegistro solicitud);
}
