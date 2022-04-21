package es.demo.servicio;

import es.demo.iservicio.IPersonalEditServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import es.demo.dao.PersonalEditDao;
import es.demo.domain.PersonalEdit;

@Service
public class PersonalEditServicioImpl implements IPersonalEditServicio {

    @Autowired
    private PersonalEditDao personalEditDao;

    @Override
    public void guardarEditar(PersonalEdit personaledit) {
        personalEditDao.save(personaledit);
    }

    @Override
    @Transactional(readOnly = true)
    public PersonalEdit buscarPersonal(PersonalEdit personaledit) {
        return personalEditDao.getById(personaledit.getID_PERSONAL());
    }
}
