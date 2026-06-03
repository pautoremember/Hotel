package project.portfolio.model;

import java.util.List;


import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
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
@Table(name = "guests") // Explícito siempre es mejor que implícito


public class Guest {

    @Nonnull
    @Id //Le dice que el ID de la BBDD es el que la variable id
    @GeneratedValue (strategy = GenerationType.AUTO) //Se generan automáticamente ids diferentes
    Long idUsuario;
    String name;
    String surname;
    Long phone;
    @ManyToMany
    List <habitaciones> habitaciones;


}
