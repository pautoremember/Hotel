package com.reservas.salas.repository;

import com.reservas.salas.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    List<Reserva> findByClienteId(Long clienteId);

    List<Reserva> findBySalaId(Long salaId);

    List<Reserva> findByEstado(Reserva.EstadoReserva estado);

    List<Reserva> findByClienteIdAndEstado(Long clienteId, Reserva.EstadoReserva estado);

    // Verificar conflictos de horario para una sala
    @Query("""
        SELECT r FROM Reserva r
        WHERE r.sala.id = :salaId
        AND r.estado IN ('PENDIENTE', 'CONFIRMADA')
        AND r.fechaInicio < :fechaFin
        AND r.fechaFin > :fechaInicio
    """)
    List<Reserva> findConflictosPorSalaYPeriodo(
            @Param("salaId") Long salaId,
            @Param("fechaInicio") LocalDateTime fechaInicio,
            @Param("fechaFin") LocalDateTime fechaFin
    );

    // Reservas en un rango de fechas
    @Query("""
        SELECT r FROM Reserva r
        WHERE r.fechaInicio >= :inicio AND r.fechaFin <= :fin
        ORDER BY r.fechaInicio ASC
    """)
    List<Reserva> findReservasEnPeriodo(
            @Param("inicio") LocalDateTime inicio,
            @Param("fin") LocalDateTime fin
    );

    // Reservas próximas (próximas 24h)
    @Query("""
        SELECT r FROM Reserva r
        WHERE r.estado = 'CONFIRMADA'
        AND r.fechaInicio BETWEEN :ahora AND :manana
        ORDER BY r.fechaInicio ASC
    """)
    List<Reserva> findReservasProximas(
            @Param("ahora") LocalDateTime ahora,
            @Param("manana") LocalDateTime manana
    );
}