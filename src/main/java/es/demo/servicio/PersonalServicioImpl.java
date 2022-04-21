package es.demo.servicio;

import es.demo.iservicio.IPersonalServicio;
import es.demo.domain.Personal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import es.demo.dao.PersonalDao;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class PersonalServicioImpl implements IPersonalServicio {

    @Autowired
    private PersonalDao personalDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<Personal> listarPersonal() {
        //nos retorna todos los datos de personal
        return (List<Personal>) personalDao.findAll();
    }

    @Override
    public Personal guardar(Personal personal) {
        //encriptamos el password antes de guardar los datos
        personal.setPASSWORD(passwordEncoder.encode(personal.getPASSWORD()));
        return personalDao.save(personal);
    }

    @Override
    @Transactional
    public void borrar(Personal personal) {
        personalDao.delete(personal);
    }

    @Override
    @Transactional(readOnly = true)
    public Personal buscarPersonal(Personal personal) {
        //buscamos personal por id
        return personalDao.getById(personal.getID_PERSONAL());
    }

    @Override
    public void registrar(Personal personal) {
        personalDao.save(personal);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Personal> personasEnProyecto(int id_proyecto) {
        //buscamos personal que y el proyecto al que pertenecen
        return personalDao.personasEnProyecto(id_proyecto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Personal> personasDisponibles(int id_proyecto) {
        //buscamos las personas que no esten en un proyecto buscado por id
        return personalDao.personasDisponibles(id_proyecto);
    }

}
