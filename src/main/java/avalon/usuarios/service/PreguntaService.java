package avalon.usuarios.service;

import avalon.usuarios.model.pojo.Pregunta;

import java.util.List;

public interface PreguntaService {
    List<Pregunta> obtenerTodasLasPreguntas();
    List<Pregunta> obtenerTodasLasPreguntasByPadre(Pregunta pregunta);
    List<Pregunta> obtenerTodasLasPreguntasRaiz();
    Pregunta guardarPregunta(Pregunta pregunta);
    Pregunta obtenerPreguntaPorId(Long id);
    void eliminarPregunta(Long id);
}
