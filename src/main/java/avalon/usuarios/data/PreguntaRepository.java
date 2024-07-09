package avalon.usuarios.data;

import avalon.usuarios.model.pojo.Notificacion;
import avalon.usuarios.model.pojo.Pregunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreguntaRepository extends JpaRepository<Pregunta, Long> {

}
