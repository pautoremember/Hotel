package project.portfolio.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "habitaciones") // Explícito siempre es mejor que implícito
public class Habitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // Long es el tipo estándar para IDs numéricos autogenerados

    @Column(nullable = false)

    private int capacity; // cuántas personas caben

    // Aquí irá la relación con Booking cuando la crees
}