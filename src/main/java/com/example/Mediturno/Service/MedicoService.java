package com.example.Mediturno.Service;

import com.example.Mediturno.DTO.medico.MedicoRequestDTO;
import com.example.Mediturno.DTO.medico.MedicoResponseDTO;

import java.util.List;

public interface MedicoService {
    MedicoResponseDTO crearMedico(MedicoRequestDTO dto);
    MedicoResponseDTO actualizarMedico(Long id, MedicoRequestDTO dto);
    void eliminarMedico(Long id);
    MedicoResponseDTO obtenerMedicoPorId(Long id);
    List<MedicoResponseDTO> obtenerMedicos();
    List<MedicoResponseDTO> obtenerMedicosPorEspecialidad(Long especialidadId);
}
