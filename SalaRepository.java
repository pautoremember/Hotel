package com.reservas.salas.repository;

import com.reservas.salas.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Long> {

    Optional<Sala> findByNombre(String nombre);

    List<Sala> findByDisponibleTrue();

    List<Sala> findByCapacidadGreaterThanEqual(Integer capacidad);

    List<Sala> findByTipo(Sala.TipoSala tipo);

    List<Sala> findByDisponibleTrueAndCapacidadGreaterThanEqual(Integer capacidad);

    boolean existsByNombre(String nombre);

    // Salas disponibles en un rango de fechas (sin reservas activas en ese período)
    @Query("""
        SELECT s FROM Sala s
        WHERE s.disponible = true
        AND s.id NOT IN (
            SELECT r.sala.id FROM Reserva r
            WHERE r.estado IN ('PENDIENTE', 'CONFIRMADA')
            AND r.fechaInicio < :fechaFin
            AND r.fechaFin > :fechaInicio
        )
    """)
    List<Sala> findSalasDisponiblesEnPeriodo(
            @Param("fechaInicio") LocalDateTime fechaInicio,
            @Param("fechaFin") LocalDateTime fechaFin
    );
}