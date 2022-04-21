package es.demo.iservicio;

import es.demo.domain.Personal;
import java.util.List;

public interface IPersonalServicio {

    public List<Personal> listarPersonal();

    public Personal guardar(Personal personal);

    public void borrar(Personal personal);

    public Personal buscarPersonal(Personal personal);

    public void registrar(Personal personal);

    public List<Personal> personasEnProyecto(int id_proyecto);

    public List<Personal> personasDisponibles(int id_proyecto);

}
