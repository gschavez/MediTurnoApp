package com.example.Mediturno.Model;

import com.example.Mediturno.Enumeraciones.EstadoTurno;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tools.jackson.databind.annotation.EnumNaming;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "turnos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Paciente que solicita la cita. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id",nullable = false)
    private Paciente paciente;

    /** Médico que atenderá la cita. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id",nullable = false)
    private Medico medico;

    /** Especialidad en la que se agenda la cita. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "especialidad_id",nullable = false)
    private Especialidad especialidad;

    /** Horario concreto del que se tomó el turno (opcional, para auditoría). */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "horario_id",nullable = false)
    private HorarioDisponible horario;

    /** Fecha de la consulta. */
    @Column(nullable = false)
    private LocalDate fecha;

    /** Hora de inicio de la cita. */
    @Column(name = " hora_inicio", nullable = false)
    private LocalTime horaInicio;

    /** Hora de finalización estimada. */
    @Column(name = " hora_fin", nullable = false)
    private LocalTime horaFin;

    /** Estado actual del turno (PENDIENTE, CONFIRMADO, etc.). */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoTurno estado;

    /** Notas internas o resumen del médico después de la consulta. */
    @Column(length = 1000)
    private String notas;

    /** Motivo de consulta ingresado por el paciente (opcional). */
    @Column(length = 500)
    private String motivoConsulta;

    /** Fecha y hora de creación del registro. */
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    /** Fecha y hora de la última modificación. */
    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @PrePersist
    protected void alCrear(){
        this.fechaCreacion = LocalDateTime.now();
    }
    @PreUpdate
    protected void alActualizar(){
        this.fechaActualizacion = LocalDateTime.now();
    }






}
