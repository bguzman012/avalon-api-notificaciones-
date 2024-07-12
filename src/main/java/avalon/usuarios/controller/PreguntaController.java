package avalon.usuarios.controller;

import avalon.usuarios.model.pojo.Pregunta;
import avalon.usuarios.model.request.PreguntaRequest;
import avalon.usuarios.service.PreguntaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PreguntaController {

    private final PreguntaService service;

    @PostMapping("/preguntas")
    public ResponseEntity<Pregunta> createPregunta(@RequestBody PreguntaRequest preguntaRequest) {
        try {
            Pregunta preguntaMapped = this.mapToPregunta(preguntaRequest, new Pregunta());
            Pregunta result = service.guardarPregunta(preguntaMapped);
            return result.getId() != null ? ResponseEntity.status(HttpStatus.CREATED).body(result)
                    : ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/preguntas")
    public ResponseEntity<List<Pregunta>> getPreguntas(@RequestParam(required = false) String raiz) {
        List<Pregunta> preguntaList;
        if (raiz == null)
            preguntaList = service.obtenerTodasLasPreguntas();
        else
            preguntaList = service.obtenerTodasLasPreguntasRaiz();


        if (!preguntaList.isEmpty()) {
            return ResponseEntity.ok(preguntaList);
        } else {
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

    @GetMapping("/padres/{padreId}/preguntas")
    public ResponseEntity<List<Pregunta>> getPreguntasByPadre(@PathVariable Long padreId) {
        if (padreId == null)
            return ResponseEntity.badRequest().build();

        Pregunta padre = this.service.obtenerPreguntaPorId(padreId);
        List<Pregunta> preguntaList = service.obtenerTodasLasPreguntasByPadre(padre);

        if (!preguntaList.isEmpty()) {
            return ResponseEntity.ok(preguntaList);
        } else {
            return ResponseEntity.ok(Collections.emptyList());
        }
    }


    @GetMapping("/preguntas/{preguntaId}")
    public ResponseEntity<Pregunta> getPregunta(@PathVariable Long preguntaId) {
        Pregunta pregunta = service.obtenerPreguntaPorId(preguntaId);

        if (pregunta != null) {
            return ResponseEntity.ok(pregunta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/preguntas/{preguntaId}")
    public ResponseEntity<Pregunta> updatePregunta(@PathVariable Long preguntaId, @RequestBody PreguntaRequest preguntaRequest) {
        Pregunta existingPregunta = service.obtenerPreguntaPorId(preguntaId);
        if (existingPregunta != null) {
            Pregunta preguntaMapped = mapToPregunta(preguntaRequest, existingPregunta);
            Pregunta updatedPregunta = service.guardarPregunta(preguntaMapped);
            return updatedPregunta != null ? ResponseEntity.ok(updatedPregunta)
                    : ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/preguntas/{preguntaId}")
    public ResponseEntity<Void> deletePregunta(@PathVariable Long preguntaId) {
        service.eliminarPregunta(preguntaId);
        return ResponseEntity.noContent().build();
    }

    private Pregunta mapToPregunta(PreguntaRequest request, Pregunta preguntaReference) {
        if (request.getPadreId() != null) {
            Pregunta padrePregunta = this.service.obtenerPreguntaPorId(request.getPadreId());
            preguntaReference.setPadre(padrePregunta);
        }else{
            preguntaReference.setPadre(null);
        }
        preguntaReference.setContenido(request.getContenido());
        preguntaReference.setRespuesta(request.getRespuesta());
        preguntaReference.setNivel(request.getNivel());

        return preguntaReference;
    }


}
