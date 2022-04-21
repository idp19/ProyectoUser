package es.demo.iservicio;

import es.demo.domain.PersonalEdit;

public interface IPersonalEditServicio {

    public void guardarEditar(PersonalEdit personaledit);

    public PersonalEdit buscarPersonal(PersonalEdit personaledit);
}
