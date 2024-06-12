package avalon.usuarios.service;

import avalon.usuarios.data.TipoNotificacionRepository;

import avalon.usuarios.model.pojo.TipoNotificacion;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoNotificacionesServiceImpl implements TipoNotificacionesService {

    private final TipoNotificacionRepository repository;

    // @Autowired
    public TipoNotificacionesServiceImpl(TipoNotificacionRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TipoNotificacion> getTiposNotificacion() {
        return repository.findAll();
    }

}
