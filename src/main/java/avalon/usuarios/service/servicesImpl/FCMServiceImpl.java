package avalon.usuarios.service.servicesImpl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Notification;

import avalon.usuarios.exception.FirebaseMessagingFailureException;
import avalon.usuarios.model.DTOs.NotificationMessageDTO;
import avalon.usuarios.model.DTOs.NotificationMessageTokenDTO;
import avalon.usuarios.model.DTOs.NotificationMessageTopicDTO;

import avalon.usuarios.service.FCMService;

import com.google.firebase.messaging.Message;

@Service
public class FCMServiceImpl implements FCMService {

    private FirebaseMessaging firebaseMessaging;

    public FCMServiceImpl(FirebaseMessaging firebaseMessaging) {
        this.firebaseMessaging = firebaseMessaging;
    }

    @Override
    public String sendNotificationByToken(NotificationMessageTokenDTO notificacion) {
        Notification notificationFCM = buildNotification(notificacion);
        Map<String, String> data = notificacion.getData();

        Message message = Message
                .builder()
                .setToken(notificacion.getToken())
                .setNotification(notificationFCM)
                .putAllData(data)
                .build();

        try {

            String response = firebaseMessaging.send(message);

            return "Success Sending Notification: " + response;
        } catch (FirebaseMessagingException e) {

            // Lanzar una excepción personalizada para manejar errores de Firebase
            throw new FirebaseMessagingFailureException(
                    "Error al enviar la notificación  personal a: "
                            + notificacion.getToken() + " : "
                            + e.getMessage(),
                    this.getClass().getName(),
                    e);
        }
    }

    @Override
    public String sendNotificationByTopic(NotificationMessageTopicDTO notificacion) {
        Notification notificationFCM = buildNotification(notificacion);

        Message message = Message
                .builder()
                .setTopic(notificacion.getTopic())
                .setNotification(notificationFCM)
                // .putAllData()
                .build();
        try {

            String response = firebaseMessaging.send(message);

            return "Success Sending Notification to topic: "
                    + notificacion.getTopic()
                    + " - "
                    + response;
        } catch (FirebaseMessagingException e) {

            // // Lanzar una excepción personalizada para manejar errores de Firebase
            throw new FirebaseMessagingFailureException(
                    "Error al enviar la notificación a Firebase por topic"
                            + notificacion.getTopic(),
                    this.getClass().getName(),
                    e);
        }
    }

    /**
     * Construye un objeto Notification a partir de un NotificationMessage.
     * 
     * <p>
     * Este método toma un objeto NotificationMessage y crea un objeto Notification
     * adecuado para ser utilizado por FCM. Si el mensaje de notificación contiene
     * una
     * imagen, esta se incluirá en la notificación resultante. En caso contrario,
     * se construirá una notificación solo con título y cuerpo.
     * </p>
     * 
     * @param notificationMessage El mensaje de notificación del cual construir el
     *                            objeto Notification.
     * @return Una instancia de Notification lista para ser enviada a través de FCM.
     */
    private Notification buildNotification(NotificationMessageDTO notificacion) {
        Notification notification;

        if (notificacion.getImage() != null) {
            notification = Notification
                    .builder()
                    .setTitle(notificacion.getTitle())
                    .setBody(notificacion.getBody())
                    .setImage(notificacion.getImage())

                    .build();
        } else {
            notification = Notification
                    .builder()
                    .setTitle(notificacion.getTitle())
                    .setBody(notificacion.getBody())
                    .build();
        }

        return notification;
    }

    // private DeviceService deviceService;
    // private EstructuraService estructuraService;

    // private HerNotificationRepository notificationRepository;
    // private HerDeviceNotificationRepository deviceNotificationRepository;
    // private HerTopicNotificationRepository topicNotificationRepository;

    // public FCMServiceImpl(FirebaseMessaging firebaseMessaging,
    // EstructuraService estructuraService,
    // DeviceService deviceService,
    // HerNotificationRepository notificationRepository,
    // HerDeviceNotificationRepository deviceNotificationRepository,
    // HerTopicNotificationRepository topicNotificationRepository) {
    // this.firebaseMessaging = firebaseMessaging;
    // this.estructuraService = estructuraService;
    // this.deviceService = deviceService;
    // this.notificationRepository = notificationRepository;
    // this.deviceNotificationRepository = deviceNotificationRepository;
    // this.topicNotificationRepository = topicNotificationRepository;

    // }

    // /**
    // * Envía notificaciones a todos los dispositivos asociados con el correo
    // * especificado.
    // *
    // * <p>
    // * Este método primero busca todos los dispositivos que están asociados con el
    // * correo proporcionado.
    // * Luego, para cada dispositivo activo encontrado, llama al metedo
    // * sendNotificationByToken para el envio de la notificación.
    // *
    // * 1. Crear el registro inicial de HerMotification para persistirlo en DB
    // *
    // * </p>
    // *
    // * @param notificationMessage El mensaje de notificación que se desea enviar.
    // * @return Una lista de respuestas por cada notificación enviada.
    // * @throws NotFoundException Si no se encontraron dispositivos activos
    // asociados
    // * al correo.
    // */
    // @Override
    // public List<String> sendNotificationByCorreo(NotificationMessage
    // notificationMessage) {

    // // List<Device> devices;

    // // devices =
    // deviceService.getAllDevicesByUserEmail(notificationMessage.getRecipientCorreo());

    // // List<String> response = new ArrayList<String>();

    // // boolean dispositivosActivosEncontrados = false;

    // // // 1. Guardar el registro inicial en HER_NOTIFICATION
    // // HerNotification herNotification =
    // HerNotification.fromNotificationMessage(notificationMessage);
    // // herNotification = notificationRepository.save(herNotification);

    // // for (Device device : devices) {
    // // if (device.getState().equals("A")) {
    // // dispositivosActivosEncontrados = true;

    // // notificationMessage.setRecipientToken(device.getFcmToken());

    // // String notificationResponse = sendNotificationByToken(herNotification,
    // device);

    // // response.add(notificationResponse);

    // // }
    // // }

    // // if (!dispositivosActivosEncontrados) {
    // // // Si no se encontraron dispositivos activos, lanza una NotFoundException
    // // throw new NotFoundException(
    // // "No hay dispositivos 'Activos' asociados al correo: " +
    // notificationMessage.getRecipientCorreo(),
    // // this.getClass().getName(), false);
    // // }
    // // return response;
    // }

    // /**
    // * Envía una notificación a un dispositivo específico basado en su token FCM.
    // *
    // * <p>
    // * Este método busca un dispositivo activo por su token y luego, y crea una
    // * instancia almacenda en DB de
    // * HerNotification, para llamar al metedo sendNotificationByToken para el
    // envio
    // * de la notificación.
    // *
    // * 1. Crear el registro inicial de HerMotification para persistirlo en DB
    // *
    // * </p>
    // *
    // * @param notificationMessage El mensaje de notificación que se desea enviar.
    // * @return Una respuesta tras enviar la notificación.
    // */
    // @Override
    // public String sendNotificationByToken(NotificationMessage
    // notificationMessage) {

    // Device device =
    // deviceService.findActiveDeviceByToken(notificationMessage.getRecipientToken());

    // // 1. Guardar el registro inicial en HER_NOTIFICATION
    // HerNotification herNotification =
    // HerNotification.fromNotificationMessage(notificationMessage);
    // herNotification = notificationRepository.save(herNotification);

    // return sendNotificationByToken(herNotification, device);
    // }

    // /**
    // * Envía una notificación a un dispositivo específico basado en su token FCM.
    // *
    // * <p>
    // * Este es un método de apoyo que facilita el envío de notificaciones
    // utilizando
    // * la información
    // * del dispositivo proporcionada.
    // *
    // * 2. Guardar el registro de HerDeviceNotification, referenciaa tabla
    // intermedia
    // *
    // * </p>
    // *
    // * @param herNotification El mensaje de notificación que se desea enviar,
    // siendo
    // * una instancia modelad de la base de datos.
    // * @param device El dispositivo al que se desea enviar la
    // * notificación.
    // * @return Una respuesta tras enviar la notificación.
    // */
    // private String sendNotificationByToken(HerNotification herNotification,
    // Device device) {

    // Notification notification = buildNotification(herNotification);
    // Map<String, String> data = herNotification.getDataAsMap();

    // Message message = Message
    // .builder()
    // .setToken(device.getFcmToken())
    // .setNotification(notification)
    // .putAllData(herNotification.getDataAsMap())
    // .build();

    // // 2. Guardar el registro en HER_USER_NOTIFICATION
    // HerDeviceNotification deviceNotification = new
    // HerDeviceNotification(herNotification, device);
    // deviceNotification = deviceNotificationRepository.save(deviceNotification);

    // try {

    // // 3. Envia la notiviacion pr medio de FirebaseMessaging.
    // String response = firebaseMessaging.send(message);

    // return "Success Sending Notification: " + response;
    // } catch (FirebaseMessagingException e) {

    // // Actualizar el estado de herNotification a 'E' (Error)
    // deviceNotification.setStatus("E");
    // deviceNotification.setModificado("hermes");
    // deviceNotification.setFechaModificacion(LocalDateTime.now());
    // deviceNotificationRepository.save(deviceNotification);

    // // Lanzar una excepción personalizada para manejar errores de Firebase
    // throw new FirebaseMessagingFailureException(
    // "Error al enviar la notificación " + deviceNotification.getCodigo() + "
    // personal a: "
    // + device.getUser().getCorreo() + "; dispositivo:"
    // + device.getId() + e.getMessage(),
    // this.getClass().getName(),
    // e);
    // }
    // }

    // /**
    // * Envía una notificación basada en un topic.
    // *
    // * <p>
    // * Este método primero verifica si el topic (representado por un código de la
    // * tabla
    // * HER_ESTRUCTURA) existe.
    // * Si existe, construye y envía una notificación a través de FirebaseMessaging
    // a
    // * todos
    // * los dispositivos subscritos a ese topic.
    // *
    // * 1. Crear el registro inicial de HerMotification para persistirlo en DB
    // *
    // * 2. Guardar el registro de HerDeviceNotification, referenciaa tabla
    // intermedia
    // *
    // * 3. Envia la notiviacion pr medio de FirebaseMessaging.
    // *
    // * </p>
    // *
    // * @param notificationMessage El mensaje de notificación que se desea enviar.
    // * @return Una respuesta tras enviar la notificación.
    // * @throws NotFoundException Si el topic no está registrado.
    // * @throws FirebaseMessagingFailureException Si hay un error al enviar la
    // * notificación a Firebase.
    // */
    // @Override
    // public String sendNotificationByTopic(NotificationMessage
    // notificationMessage) {

    // Optional<Estructura> estructuraOpt =
    // estructuraService.findByCodigo(notificationMessage.getRecipientTopic());

    // // 1. Guardar el registro inicial en HER_NOTIFICATION
    // HerNotification herNotification =
    // HerNotification.fromNotificationMessage(notificationMessage);
    // herNotification = notificationRepository.save(herNotification);

    // if (estructuraOpt.isPresent()) {

    // Estructura estructura = estructuraOpt.get();

    // /// TODO to tes to delete 1L
    // herNotification.setTitle(notificationMessage.getTitle() +
    // estructura.getDescripcion());

    // // 2. Guardar el registro en HER_TOPIC_NOTIFICATION
    // HerTopicNotification topicNotification = new
    // HerTopicNotification(herNotification, estructura);
    // topicNotification = topicNotificationRepository.save(topicNotification);

    // Notification notification = buildNotification(herNotification);
    // Map<String, String> data = herNotification.getDataAsMap();

    // Message message = Message

    // .builder()

    // .setTopic(estructura.getId().toString())
    // .setNotification(notification)
    // .putAllData(notificationMessage.getData())
    // .build();
    // try {

    // String response = firebaseMessaging.send(message);
    // // 3. Envia la notiviacion pr medio de FirebaseMessaging.
    // return "Success Sending Notification to topic: "
    // + notificationMessage.getRecipientTopic()
    // + " - "
    // + response;
    // } catch (FirebaseMessagingException e) {

    // // Actualizar el estado de herNotification a 'E' (Error)
    // topicNotification.setStatus("E");
    // topicNotification.setModificado("hermes");
    // topicNotification.setFechaModificacion(LocalDateTime.now());
    // topicNotificationRepository.save(topicNotification);

    // // Lanzar una excepción personalizada para manejar errores de Firebase
    // throw new FirebaseMessagingFailureException(
    // "Error al enviar la notificación a Firebase por topic"
    // + topicNotification.getEstructura().getId().toString(),
    // this.getClass().getName(),
    // e);
    // }
    // } else {
    // throw new NotFoundException(
    // "No existe el topic " + notificationMessage.getRecipientTopic() + "
    // registrado",
    // this.getClass().getName(), true);
    // }

    // }

    // /**
    // * Construye un objeto Notification a partir de un NotificationMessage.
    // *
    // * <p>
    // * Este método toma un objeto NotificationMessage y crea un objeto
    // Notification
    // * adecuado para ser utilizado por FCM. Si el mensaje de notificación contiene
    // * una
    // * imagen, esta se incluirá en la notificación resultante. En caso contrario,
    // * se construirá una notificación solo con título y cuerpo.
    // * </p>
    // *
    // * @param notificationMessage El mensaje de notificación del cual construir el
    // * objeto Notification.
    // * @return Una instancia de Notification lista para ser enviada a través de
    // FCM.
    // */
    // private Notification buildNotification(HerNotification herNotification) {
    // Notification notification;

    // if (herNotification.hasImage()) {
    // notification = Notification
    // .builder()
    // .setTitle(herNotification.getTitle())
    // .setBody(herNotification.getBody())
    // .setImage(herNotification.getImage())

    // .build();
    // } else {
    // notification = Notification
    // .builder()
    // .setTitle(herNotification.getTitle())
    // .setBody(herNotification.getBody())
    // .build();
    // }

    // return notification;
    // }
}
