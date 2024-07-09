package avalon.usuarios.service;

import avalon.usuarios.data.InteraccionRepository;
import avalon.usuarios.model.pojo.Interaccion;
import avalon.usuarios.model.pojo.Pregunta;
import avalon.usuarios.service.InteraccionService;
import avalon.usuarios.service.PreguntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class InteraccionServiceImpl implements InteraccionService {

    @Autowired
    private InteraccionRepository interaccionRepository;

    @Autowired
    private PreguntaService preguntaService;

    @Override
    public Interaccion guardarInteraccion(Interaccion interaccion) {
        return interaccionRepository.save(interaccion);
    }

    @Override
    public Interaccion obtenerInteraccionPorId(Long id) {
        return interaccionRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarInteraccion(Long id) {
        interaccionRepository.deleteById(id);
    }

    @Override
    public List<Interaccion> obtenerInteraccionesPorPadrePorPreguntaId(Long preguntaId) {
        Pregunta pregunta = preguntaService.obtenerPreguntaPorId(preguntaId);
        if (pregunta != null) {
            return interaccionRepository.findByPregunta(pregunta);
        }
        return null;
    }
}
