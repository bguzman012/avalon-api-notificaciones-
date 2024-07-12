package avalon.usuarios.data;

import avalon.usuarios.model.pojo.Notificacion;
import avalon.usuarios.model.pojo.Pregunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PreguntaRepository extends JpaRepository<Pregunta, Long> {

    List<Pregunta> findAllByPadreIsNull();
    List<Pregunta> findAllByPadre(Pregunta padre);

}
