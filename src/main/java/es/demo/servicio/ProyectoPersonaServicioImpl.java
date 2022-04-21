package es.demo.servicio;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import es.demo.dao.ProyectoPersonaDao;
import es.demo.domain.ProyectoPersona;
import es.demo.iservicio.IProyectoPersonaServicio;

@Service
public class ProyectoPersonaServicioImpl implements IProyectoPersonaServicio {

    @Autowired
    private ProyectoPersonaDao proyectoPersonaDao;

    @Override
    @Transactional(readOnly = true)
    public List<ProyectoPersona> listarProyectoPersona() {
        return (List<ProyectoPersona>) proyectoPersonaDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(ProyectoPersona proyectopersona) {
        proyectoPersonaDao.save(proyectopersona);
    }

    @Override
    @Transactional
    public void borrar(ProyectoPersona proyectopersona) {
        proyectoPersonaDao.delete(proyectopersona);
    }

    @Override
    @Transactional(readOnly = true)
    public ProyectoPersona buscarProyectoPersona(ProyectoPersona proyectopersona) {
        return proyectoPersonaDao.findById(proyectopersona.getID_PRO_PER()).orElse(null);
    }

    @Override
    @Transactional
    public void borrarProyectoPersona(int id_personal) {
        proyectoPersonaDao.borrarProyectoPersona(id_personal);

    }

}
