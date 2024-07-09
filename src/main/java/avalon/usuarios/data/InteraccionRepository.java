package avalon.usuarios.data;

import avalon.usuarios.model.pojo.Interaccion;
import avalon.usuarios.model.pojo.Pregunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InteraccionRepository extends JpaRepository<Interaccion, Long> {

}
