package avalon.usuarios.service;

import avalon.usuarios.data.PreguntaRepository;
import avalon.usuarios.model.pojo.Pregunta;
import avalon.usuarios.service.PreguntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreguntaServiceImpl implements PreguntaService {

    @Autowired
    private PreguntaRepository preguntaRepository;

    @Override
    public List<Pregunta> obtenerTodasLasPreguntas() {
        return preguntaRepository.findAll();
    }

    @Override
    public List<Pregunta> obtenerTodasLasPreguntasByPadre(Pregunta padre) {
        return preguntaRepository.findAllByPadre(padre);
    }


    @Override
    public List<Pregunta> obtenerTodasLasPreguntasRaiz() {
        return preguntaRepository.findAllByPadreIsNull();
    }


    @Override
    public Pregunta guardarPregunta(Pregunta pregunta) {
        return preguntaRepository.save(pregunta);
    }

    @Override
    public Pregunta obtenerPreguntaPorId(Long id) {
        return preguntaRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarPregunta(Long id) {
        preguntaRepository.deleteById(id);
    }
}
