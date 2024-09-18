package avalon.usuarios.service;

import avalon.usuarios.data.NotificacionRepository;
import avalon.usuarios.data.TipoNotificacionRepository;
import avalon.usuarios.model.DTOs.NotificationMessageTokenDTO;
import avalon.usuarios.model.DTOs.NotificationMessageTopicDTO;
import avalon.usuarios.model.pojo.Notificacion;
import avalon.usuarios.model.pojo.TipoNotificacion;
import avalon.usuarios.model.request.CreateNotificacionRequest;
import avalon.usuarios.model.request.UpdateNotificacionRequest;

import avalon.usuarios.service.servicesImpl.FCMServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NotificacionesServiceImpl implements NotificacionesService {

    private final NotificacionRepository repository;
    private final TipoNotificacionRepository tipoNotificacionRepository;

    @Autowired
    private FCMServiceImpl fcmService;

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
        notificacion.setUsuarioEnvia(request.getUsuarioEnvia());
        notificacion.setUsuarioAseguradorId(request.getUsuarioAseguradorId());

        NotificationMessageTopicDTO notificationMessageTopicDTO = new NotificationMessageTopicDTO();
        notificationMessageTopicDTO.setTitle(request.getAsunto());
        notificationMessageTopicDTO.setBody(request.getMensaje());
        notificationMessageTopicDTO.setEmitterUser(request.getUsuarioEnvia());

        if (request.getUsuarioAseguradorId() != null && !request.getUsuarioAseguradorId().isEmpty())
            notificationMessageTopicDTO.setTopic(request.getUsuarioAseguradorId());
        else
            notificationMessageTopicDTO.setTopic(tipoNotificacion.getCodigo());

        String response = fcmService.sendNotificationByTopic(notificationMessageTopicDTO);

//        NotificationMessageTokenDTO notificationMessageTokenDTO = new NotificationMessageTokenDTO();
//        notificationMessageTokenDTO.setTitle(request.getAsunto());
//        notificationMessageTokenDTO.setBody(request.getMensaje());
//        notificationMessageTokenDTO.setEmitterUser(request.getUsuarioEnvia());
//        notificationMessageTokenDTO.setToken("dHXBiSdCTLqLljWc58oejt:APA91bGsiYuKxpFpXGqNgbJYEBAh2Nnn0OxFEnu2d-h1h2pyblZOuNLKxoWe_4f_ziW9-S7V0NE5DmAXr3zJz3jstN9RL86aer9UxAvAG8zfceKA4c0TBrzNx3BZfXoiKaf-C_R0MTLc");
//        Map<String, String> data = new HashMap<>();
//        notificationMessageTokenDTO.setData(data);
//
//        String response = fcmService.sendNotificationByToken(notificationMessageTokenDTO);

        notificacion.setRespuesta(response);

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
