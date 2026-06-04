
package
import org.springframework.stereotype.Repository;

import jakarta.persistence.IdClass;

@Repository

public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {


}

