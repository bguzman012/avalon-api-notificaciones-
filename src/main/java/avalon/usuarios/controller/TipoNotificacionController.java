package avalon.usuarios.controller;

import avalon.usuarios.model.pojo.Notificacion;
import avalon.usuarios.model.pojo.TipoNotificacion;
import avalon.usuarios.model.request.CreateNotificacionRequest;
import avalon.usuarios.model.request.UpdateNotificacionRequest;
import avalon.usuarios.service.NotificacionesServiceImpl;
import avalon.usuarios.service.TipoNotificacionesServiceImpl;
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
public class TipoNotificacionController {

    private final TipoNotificacionesServiceImpl service;

    @GetMapping("/tiposNotificacion")
    public ResponseEntity<List<TipoNotificacion>> getTiposNotificacion() {
        List<TipoNotificacion> tiposNotificacionList = service.getTiposNotificacion();
        System.out.println("");
        if (!tiposNotificacionList.isEmpty()) {
            return ResponseEntity.ok(tiposNotificacionList);
        } else {
            return ResponseEntity.ok(Collections.emptyList());
        }
    }
}
