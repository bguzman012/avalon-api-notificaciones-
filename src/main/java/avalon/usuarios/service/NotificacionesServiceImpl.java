package avalon.usuarios.service;

import avalon.usuarios.data.NotificacionRepository;
import avalon.usuarios.data.TipoNotificacionRepository;
import avalon.usuarios.model.pojo.Notificacion;
import avalon.usuarios.model.pojo.TipoNotificacion;
import avalon.usuarios.model.request.CreateNotificacionRequest;
import avalon.usuarios.model.request.UpdateNotificacionRequest;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificacionesServiceImpl implements NotificacionesService {

    private final NotificacionRepository repository;
    private final TipoNotificacionRepository tipoNotificacionRepository;

    // @Autowired
    public NotificacionesServiceImpl(NotificacionRepository repository,
            TipoNotificacionRepository tipoNotificacionRepository) {
        this.repository = repository;
        this.tipoNotificacionRepository = tipoNotificacionRepository;
    }

    @Override
    public List<Notificacion> getNotificaciones() {
        return repository.findAll();
    }

    @Override
    public Notificacion getNotificacion(Long notificacionId) {
        return repository.findById(notificacionId).orElse(null);
    }

    @Override
    public Notificacion createNotificacion(CreateNotificacionRequest request) {
        TipoNotificacion tipoNotificacion = tipoNotificacionRepository.findById(request.getTipoNotificacionId())
                .orElse(null);

        if (tipoNotificacion == null)
            return null;

        Notificacion notificacion = new Notificacion();
        notificacion.setAsunto(request.getAsunto());
        notificacion.setMensaje(request.getMensaje());
        notificacion.setTipoNotificacion(tipoNotificacion);
        notificacion.setUsuarioAseguradorId(request.getUsuarioAseguradorId());

        return repository.save(notificacion);
    }

    @Override
    public Notificacion updateNotificacion(Notificacion notificacion, UpdateNotificacionRequest request) {
        TipoNotificacion tipoNotificacion = tipoNotificacionRepository.findById(request.getTipoNotificacionId())
                .orElse(null);

        if (tipoNotificacion == null)
            return null;

        notificacion.setAsunto(request.getAsunto());
        notificacion.setMensaje(request.getMensaje());
        notificacion.setTipoNotificacion(tipoNotificacion);
        notificacion.setUsuarioAseguradorId(request.getUsuarioAseguradorId());

        return repository.save(notificacion);
    }

    @Override
    public void deleteNotificacion(Long notificacionId) {
        this.repository.deleteById(notificacionId);
    }
}
