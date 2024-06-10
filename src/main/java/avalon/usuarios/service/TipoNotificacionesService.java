package avalon.usuarios.service;

import avalon.usuarios.model.pojo.Notificacion;
import avalon.usuarios.model.pojo.TipoNotificacion;
import avalon.usuarios.model.request.CreateNotificacionRequest;
import avalon.usuarios.model.request.UpdateNotificacionRequest;

import java.util.List;

public interface TipoNotificacionesService {

    List<TipoNotificacion> getTiposNotificacion();

}
