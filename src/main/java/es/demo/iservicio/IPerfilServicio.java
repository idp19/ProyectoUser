package es.demo.iservicio;

import es.demo.domain.Perfil;
import java.util.List;

public interface IPerfilServicio {

    public List<Perfil> listarPerfil();

    public void guardar(Perfil perfil);

    public void borrar(Perfil perfil);

    public Perfil buscarPerfil(Perfil perfil);

}
