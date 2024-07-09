package avalon.usuarios.controller;

import avalon.usuarios.model.pojo.Interaccion;
import avalon.usuarios.model.pojo.Pregunta;
import avalon.usuarios.model.request.InteraccionRequest;
import avalon.usuarios.model.request.PreguntaRequest;
import avalon.usuarios.service.InteraccionService;
import avalon.usuarios.service.PreguntaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class InteraccionController {

    private final InteraccionService service;
    @Autowired
    private PreguntaService preguntaService;

    @PostMapping("/interacciones")
    public ResponseEntity<Interaccion> createInteraccion(@RequestBody InteraccionRequest interaccionRequest) {
        try {
            Interaccion interaccionMapped = this.mapToInteraccion(interaccionRequest, new Interaccion());
            Interaccion result = service.guardarInteraccion(interaccionMapped);
            return result.getId() != null ? ResponseEntity.status(HttpStatus.CREATED).body(result)
                    : ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/preguntas/{preguntaId}/interacciones")
    public ResponseEntity<List<Interaccion>> obtenerInteraccionesPorPadrePorPreguntaId(@PathVariable Long preguntaId) {
        List<Interaccion> preguntaList = service.obtenerInteraccionesPorPadrePorPreguntaId(preguntaId);

        if (!preguntaList.isEmpty()) {
            return ResponseEntity.ok(preguntaList);
        } else {
            return ResponseEntity.ok(Collections.emptyList());
        }
    }


    @PutMapping("/interacciones/{inteaccionId}")
    public ResponseEntity<Interaccion> updatePregunta(@PathVariable Long inteaccionId, @RequestBody InteraccionRequest interaccionRequest) {
        Interaccion existingInteraccion = service.obtenerInteraccionPorId(inteaccionId);
        if (existingInteraccion != null) {
            Interaccion interaccionMapped = mapToInteraccion(interaccionRequest, existingInteraccion);
            Interaccion updatedInteraccion = service.guardarInteraccion(interaccionMapped);
            return updatedInteraccion != null ? ResponseEntity.ok(updatedInteraccion)
                    : ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/interacciones/{inteaccionId}")
    public ResponseEntity<Void> deletePregunta(@PathVariable Long inteaccionId) {
        service.eliminarInteraccion(inteaccionId);
        return ResponseEntity.noContent().build();
    }

    private Interaccion mapToInteraccion(InteraccionRequest request, Interaccion interaccionReference) {
        Pregunta pregunta = this.preguntaService.obtenerPreguntaPorId(request.getPreguntaId());
        Pregunta respuesta = this.preguntaService.obtenerPreguntaPorId(request.getRespuestaId());

        interaccionReference.setPregunta(pregunta);
        interaccionReference.setRespuesta(respuesta);
        return interaccionReference;
    }


}
