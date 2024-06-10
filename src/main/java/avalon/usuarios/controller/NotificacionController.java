package avalon.usuarios.controller;

import avalon.usuarios.model.pojo.Notificacion;
import avalon.usuarios.model.request.CreateNotificacionRequest;
import avalon.usuarios.model.request.UpdateNotificacionRequest;
import avalon.usuarios.service.NotificacionesService;
import avalon.usuarios.service.NotificacionesServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class NotificacionController {

    private final NotificacionesServiceImpl service;

    @PostMapping("/notificaciones")
    public ResponseEntity<Notificacion> createNotificacion(@RequestBody CreateNotificacionRequest request) {
        try {
            Notificacion result = service.createNotificacion(request);
            return result.getId() != null ? ResponseEntity.status(HttpStatus.CREATED).body(result) : ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/notificaciones")
    public ResponseEntity<List<Notificacion>> getNotificaciones() {
        List<Notificacion> notificacionList = service.getNotificaciones();

        if (!notificacionList.isEmpty()) {
            return ResponseEntity.ok(notificacionList);
        } else {
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

    @GetMapping("/notificaciones/{notificacionId}")
    public ResponseEntity<Notificacion> getNotificacion(@PathVariable Long notificacionId) {
        Notificacion notificacion = service.getNotificacion(notificacionId);

        if (notificacion != null) {
            return ResponseEntity.ok(notificacion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/notificaciones/{notificacionId}")
    public ResponseEntity<Notificacion> updateNotificacion(@PathVariable Long notificacionId, @RequestBody UpdateNotificacionRequest request) {
        Notificacion notificacion = service.getNotificacion(notificacionId);

        if (notificacion != null) {
            Notificacion notificacionUpdate = service.updateNotificacion(notificacion, request);
            return notificacionUpdate != null ? ResponseEntity.ok(notificacionUpdate) : ResponseEntity.badRequest().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }

    }

}
