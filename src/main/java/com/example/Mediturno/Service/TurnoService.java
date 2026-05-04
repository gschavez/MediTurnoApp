package com.example.Mediturno.Service;

import com.example.Mediturno.DTO.turno.TurnoRequestDTO;
import com.example.Mediturno.DTO.turno.TurnoResponseDTO;
import com.example.Mediturno.Enumeraciones.EstadoTurno;

import java.time.LocalDate;
import java.util.List;

/**
 * Contrato del servicio de Turnos.
 * Define QUÉ puede hacer el sistema con los turnos.
 * El CÓMO queda en la implementación (TurnoServiceImpl).
 */
public interface TurnoService {

    /** Agendamiento de un nuevo turno */
    TurnoResponseDTO crearTurno(TurnoRequestDTO dto);

    /** Consultas */
    TurnoResponseDTO obtenerTurnoPorId(Long id);
    List<TurnoResponseDTO> obtenerTurnosPorPaciente(Long pacienteId);
    List<TurnoResponseDTO> obtenerTurnosPorMedicoYFecha(Long medicoId, LocalDate fecha);
    List<TurnoResponseDTO> obtenerTodos();

    /** Cambio de estado (CONFIRMADO, EN_CURSO, ATENDIDO, etc.) */
    TurnoResponseDTO cambiarEstado(Long id, EstadoTurno nuevoEstado);

    /** Cancelación del turno */
    void cancelarTurno(Long id);
}
