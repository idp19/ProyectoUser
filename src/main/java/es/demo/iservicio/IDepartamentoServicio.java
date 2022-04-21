package es.demo.iservicio;

import es.demo.domain.Departamento;
import java.util.List;

public interface IDepartamentoServicio {

    public List<Departamento> listarDepartamento();

    public void guardar(Departamento departamento);

    public void borrar(Departamento departamento);

    public Departamento buscarDepartamento(Departamento departamento);

}
