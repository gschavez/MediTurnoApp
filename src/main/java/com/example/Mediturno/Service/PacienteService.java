package com.example.Mediturno.Service;

import com.example.Mediturno.DTO.paciente.PacienteRequestDTO;
import com.example.Mediturno.DTO.paciente.PacienteResponseDTO;
import java.util.List;

public interface PacienteService {
    PacienteResponseDTO crearPaciente(PacienteRequestDTO dto);
    PacienteResponseDTO actualizarPaciente(Long id, PacienteRequestDTO dto);
    void eliminarPaciente(Long id);
    PacienteResponseDTO obtenerPacientePorId(Long id);
    PacienteResponseDTO obtenerPacientePorCedula(String cedula);
    List<PacienteResponseDTO> obtenerPacientes();
    PacienteResponseDTO obtenerPacientePorNombreUsuario(String nombreUsuario);
}