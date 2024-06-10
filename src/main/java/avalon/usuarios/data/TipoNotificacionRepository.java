package avalon.usuarios.data;

import avalon.usuarios.model.pojo.TipoNotificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoNotificacionRepository extends JpaRepository<TipoNotificacion, Long> {

}
