package com.example.Mediturno.Service;

import com.example.Mediturno.DTO.especialidad.EspecialidadRequestDTO;
import com.example.Mediturno.DTO.especialidad.EspecialidadResponseDTO;

import java.util.List;

public interface EspecialidadService {
    EspecialidadResponseDTO crearEspecialidad(EspecialidadRequestDTO dto);
    EspecialidadResponseDTO actualizarEspecialidad(Long id, EspecialidadRequestDTO dto);
    void eliminarEspecialidad(Long id);
    EspecialidadResponseDTO obtenerEspecialidadPorId(Long id);
    List<EspecialidadResponseDTO> obtenerEspecialidades();
}
