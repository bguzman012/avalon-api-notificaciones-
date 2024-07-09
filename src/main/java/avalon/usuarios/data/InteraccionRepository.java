package avalon.usuarios.data;

import avalon.usuarios.model.pojo.Interaccion;
import avalon.usuarios.model.pojo.Pregunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InteraccionRepository extends JpaRepository<Interaccion, Long> {

    List<Interaccion> findByPregunta(Pregunta pregunta);

}
