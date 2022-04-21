package es.demo.dao;

import es.demo.domain.Proyecto;
import org.springframework.data.repository.CrudRepository;

public interface ProyectoDao extends CrudRepository<Proyecto, Integer> {

}
