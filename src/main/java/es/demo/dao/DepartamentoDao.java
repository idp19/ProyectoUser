package es.demo.dao;

import es.demo.domain.Departamento;
import org.springframework.data.repository.CrudRepository;

public interface DepartamentoDao extends CrudRepository<Departamento, Integer> {

}
