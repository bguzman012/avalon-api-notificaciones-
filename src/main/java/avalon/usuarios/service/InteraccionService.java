package avalon.usuarios.service;

import avalon.usuarios.model.pojo.Interaccion;

import java.util.List;

public interface InteraccionService {
    Interaccion guardarInteraccion(Interaccion interaccion);
    Interaccion obtenerInteraccionPorId(Long id);
    void eliminarInteraccion(Long id);
    List<Interaccion> obtenerInteraccionesPorPadrePorPreguntaId(Long preguntaId);
}
