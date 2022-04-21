package es.demo.servicio;

import es.demo.iservicio.IPerfilServicio;
import es.demo.domain.Perfil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import es.demo.dao.PerfilDao;

@Service
public class PerfilServicioImpl implements IPerfilServicio {

    @Autowired
    private PerfilDao perfilDao;

    @Override
    @Transactional(readOnly = true)
    public List<Perfil> listarPerfil() {
        return (List<Perfil>) perfilDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Perfil perfil) {
        perfilDao.save(perfil);
    }

    @Override
    @Transactional
    public void borrar(Perfil perfil) {
        perfilDao.delete(perfil);
    }

    @Override
    @Transactional(readOnly = true)
    public Perfil buscarPerfil(Perfil perfil) {
        return perfilDao.findById(perfil.getID_PERFIL()).orElse(null);
    }

}
