package es.demo.dao;

import es.demo.domain.Personal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalDao extends JpaRepository<Personal, Integer> {

    @Query(value = "SELECT * FROM personal join proyecto_persona WHERE personal.ID_PERSONAL=proyecto_persona.ID_PERSONAL and proyecto_persona.ID_PROYECTO=:id_proyecto", nativeQuery = true)
    List<Personal> personasEnProyecto(@Param("id_proyecto") int id_proyecto);

    @Query(value = "SELECT * FROM personal WHERE personal.ID_PERSONAL not in (select proyecto_persona.ID_PERSONAL from proyecto_persona where proyecto_persona.ID_PROYECTO=:id_proyecto)", nativeQuery = true)
    List<Personal> personasDisponibles(@Param("id_proyecto") int id_proyecto);
}
