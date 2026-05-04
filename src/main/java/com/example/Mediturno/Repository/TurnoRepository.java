package com.example.Mediturno.Repository;

import com.example.Mediturno.Model.Medico;
import com.example.Mediturno.Model.Paciente;
import com.example.Mediturno.Model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {


    /**
     * Obtiene todos los turnos de un paciente, ordenados del más reciente al más antiguo.
     */
    List<Turno> findByPacienteOrderByFechaDescHoraInicioDesc(Paciente paciente);

    /**
     * Obtiene los turnos de un médico para una fecha específica (su agenda diaria).
     */
    List<Turno> findByMedicoAndFecha(Medico medico, LocalDate fecha);


    /**
     * Cuenta cuántos turnos activos (no cancelados ni no asistidos) se superponen
     * con un rango horario dado. Se usa para verificar disponibilidad antes de agendar.
     * @param medico médico a verificar
     * @param fecha fecha del turno
     * @param horaInicio hora de inicio del nuevo turno propuesto
     * @param horaFin hora de fin del nuevo turno propuesto
     * @return cantidad de turnos que ocupan ese espacio
     */
    @Query("SELECT COUNT(t) FROM Turno t WHERE t.medico = :medico AND t.fecha = :fecha " +
    "AND t.estado NOT IN ('CANCELADO', 'NO_ASISTIO')"+
    " AND t.horaInicio < : horaFin AND t.horaFin > : horaInicio")
    long contarSolapados(@Param("medico")Medico medico,
                         @Param("fecha") LocalDate fecha,
                         @Param("horaInicio")LocalTime horaInicio,
                         @Param("horaFin")LocalTime horaFin);
}
