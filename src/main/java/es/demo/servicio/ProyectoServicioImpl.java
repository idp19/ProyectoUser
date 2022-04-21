package es.demo.servicio;

import es.demo.iservicio.IProyectoServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import es.demo.dao.ProyectoDao;
import es.demo.domain.Proyecto;

@Service
public class ProyectoServicioImpl implements IProyectoServicio {

    @Autowired
    private ProyectoDao proyectoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Proyecto> listarProyecto() {
        return (List<Proyecto>) proyectoDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Proyecto proyecto) {
        proyectoDao.save(proyecto);
    }

    @Override
    @Transactional
    public void borrar(Proyecto proyecto) {
        proyectoDao.delete(proyecto);
    }

    @Override
    @Transactional(readOnly = true)
    public Proyecto buscarProyecto(Proyecto proyecto) {
        return proyectoDao.findById(proyecto.getID_PROYECTO()).orElse(null);
    }

}
