package es.demo.servicio;

import es.demo.iservicio.IDepartamentoServicio;
import es.demo.domain.Departamento;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import es.demo.dao.DepartamentoDao;

@Service
public class DepartamentoServicioImpl implements IDepartamentoServicio {

    @Autowired
    private DepartamentoDao departamentoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Departamento> listarDepartamento() {
        return (List<Departamento>) departamentoDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Departamento departamento) {
        departamentoDao.save(departamento);
    }

    @Override
    @Transactional
    public void borrar(Departamento departamento) {
        departamentoDao.delete(departamento);
    }

    @Override
    @Transactional(readOnly = true)
    public Departamento buscarDepartamento(Departamento departamento) {
        return departamentoDao.findById(departamento.getID_DEPARTAMENTO()).orElse(null);
    }

}
