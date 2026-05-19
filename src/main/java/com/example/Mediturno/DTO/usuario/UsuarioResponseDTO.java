package com.example.Mediturno.DTO.usuario;

import com.example.Mediturno.Enumeraciones.Rol;
import com.example.Mediturno.Model.Usuario;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * DTO de respuesta para Usuario.
 * Evita la referencia circular Usuario → Paciente → Usuario
 * que causaba el error de JSON malformado.
 */
@Data
public class UsuarioResponseDTO {

    private Long id;
    private String nombreUsuario;
    private String email;
    private String nombre;
    private String apellido;
    private Rol rol;
    private Boolean activo;
    private LocalDateTime fechaCreacion;

    /**
     * Convierte un Usuario (entidad) a UsuarioResponseDTO.
     * Solo incluye campos planos, sin relaciones anidadas.
     */
    public static UsuarioResponseDTO fromUsuario(Usuario usuario) {
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setId(usuario.getId());
        dto.setNombreUsuario(usuario.getNombreUsuario());
        dto.setEmail(usuario.getEmail());
        dto.setRol(usuario.getRol());
        dto.setActivo(usuario.getActivo());
        dto.setFechaCreacion(usuario.getFechaCreacion());

        // nombre y apellido vienen del perfil vinculado (paciente o médico)
        // si ninguno existe, quedan null
        if (usuario.getPaciente() != null) {
            dto.setNombre(usuario.getPaciente().getNombre());
            dto.setApellido(usuario.getPaciente().getApellido());
        } else if (usuario.getMedico() != null) {
            dto.setNombre(usuario.getMedico().getNombre());
            dto.setApellido(usuario.getMedico().getApellido());
        }

        return dto;
    }
}