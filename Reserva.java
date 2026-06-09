
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
    import java.time.LocalDateTime;
    import lombok.ToString;

    @NoArgsConstructor
    @AllArgsConstructor
        @Getter
    @Setter
    @ToString
    @Entity
    @Table(name = "reservas")


    public class Reserva {

        @Nonnull
        @Id //Le dice que el ID de la BBDD es el que la variable id
        @GeneratedValue (strategy = GenerationType.AUTO) //Se generan automáticamente ids diferentes
        Long idReserva;
        LocalDateTime startDate;
        LocalDateTime endDate;
        @ManyToMany
        List <Guest> guests;
        @ManyToMany
        List <Habitacion> habitaciones;
    

    }
