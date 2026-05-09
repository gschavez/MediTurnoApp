package com.example.Mediturno.Repository;

import com.example.Mediturno.Model.HorarioDisponible;
import com.example.Mediturno.Model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HorarioDisponibleRepository extends JpaRepository<HorarioDisponible, Long> {


    /**
     * Obtiene los horarios activos de un médico para una fecha determinada.
     * Incluye tanto los horarios recurrentes (por día de la semana) como
     * los específicos para esa fecha exacta.
     * @param medico el médico
     * @param diaSemana número del día de la semana (1=lunes..7=domingo)
     * @param fecha la fecha concreta para buscar horarios especiales
     * @return lista de horarios disponibles para ese médico en esa fecha
     */
    @Query("SELECT h FROM HorarioDisponible h WHERE h.medico = : medico AND h.activo = true "+
            "AND((h.fechaEspecifica IS NULL AND h.diaSemana = : diaSemana)OR h.fechaEspecifica = :fecha)")
            List<HorarioDisponible> findActivosPorMedicoYFecha(
            @Param("medico") Medico medico,
            @Param("diaSemana") int diaSemana,
            @Param("fecha")LocalDate fecha);
}
