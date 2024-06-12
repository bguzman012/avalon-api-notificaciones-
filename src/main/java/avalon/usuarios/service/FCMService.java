package avalon.usuarios.service;

import avalon.usuarios.model.DTOs.NotificationMessageTokenDTO;
import avalon.usuarios.model.DTOs.NotificationMessageTopicDTO;

public interface FCMService {
    public String sendNotificationByToken(NotificationMessageTokenDTO notificacion);

    public String sendNotificationByTopic(NotificationMessageTopicDTO notificacion);

}
