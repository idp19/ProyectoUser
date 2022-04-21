package es.demo.iservicio;

import es.demo.domain.Proyecto;
import java.util.List;

public interface IProyectoServicio {

    public List<Proyecto> listarProyecto();

    public void guardar(Proyecto proyecto);

    public void borrar(Proyecto proyecto);

    public Proyecto buscarProyecto(Proyecto proyecto);
}
