package avalon.usuarios.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import avalon.usuarios.model.DTOs.NotificationMessageDTO;
import avalon.usuarios.model.DTOs.NotificationMessageTokenDTO;
import avalon.usuarios.model.DTOs.NotificationMessageTopicDTO;
import avalon.usuarios.service.FCMService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/fcm")
public class FCMNotificationsController {

    private FCMService firebaseMessagingService;

    public FCMNotificationsController(FCMService firebaseMessagingService) {
        this.firebaseMessagingService = firebaseMessagingService;
    }

    @PostMapping("/sendToToken")
    public ResponseEntity<String> sendNotificationByToken(
            @Valid @RequestBody NotificationMessageTokenDTO notificationMessageDTO) {
        System.out.println(notificationMessageDTO);

        return ResponseEntity
                .ok(firebaseMessagingService.sendNotificationByToken(notificationMessageDTO));
    }

    @PostMapping("/sendToTopic")
    public ResponseEntity<String> subscribeToTopic(

            @Valid @RequestBody NotificationMessageTopicDTO notificationMessageDTO) {
        System.out.println(notificationMessageDTO);

        return ResponseEntity
                .ok(firebaseMessagingService.sendNotificationByTopic(notificationMessageDTO));
    }

}
