package avalon.usuarios.service;

import avalon.usuarios.model.pojo.Notificacion;
import avalon.usuarios.model.request.CreateNotificacionRequest;
import avalon.usuarios.model.request.UpdateNotificacionRequest;

import java.util.List;

public interface NotificacionesService {

    List<Notificacion> getNotificaciones();
    Notificacion getNotificacion(Long notificacionId);
    Notificacion createNotificacion(CreateNotificacionRequest request);
    Notificacion updateNotificacion(Notificacion notificacion, UpdateNotificacionRequest request);
    void deleteNotificacion(Long notificacionId);
}
