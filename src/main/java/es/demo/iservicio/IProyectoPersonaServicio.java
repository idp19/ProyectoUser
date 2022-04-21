package es.demo.iservicio;

import es.demo.domain.ProyectoPersona;
import java.util.List;

public interface IProyectoPersonaServicio {

    public List<ProyectoPersona> listarProyectoPersona();

    public void guardar(ProyectoPersona proyectopersona);

    public void borrar(ProyectoPersona proyectopersona);

    public ProyectoPersona buscarProyectoPersona(ProyectoPersona proyectopersona);

    public void borrarProyectoPersona(int id_personal);
}
