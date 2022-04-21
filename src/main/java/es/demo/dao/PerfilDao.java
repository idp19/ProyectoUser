package es.demo.dao;

import es.demo.domain.Perfil;
import org.springframework.data.repository.CrudRepository;

public interface PerfilDao extends CrudRepository<Perfil, Integer> {

}
