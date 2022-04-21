package es.demo.dao;

import es.demo.domain.ProyectoPersona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProyectoPersonaDao extends JpaRepository<ProyectoPersona, Integer> {

    @Modifying
    @Query(value = "Delete FROM empresa.proyecto_persona where proyecto_persona.ID_PERSONAL=:id_personal", nativeQuery = true)
    public void borrarProyectoPersona(@Param("id_personal") int id_personal);

}
