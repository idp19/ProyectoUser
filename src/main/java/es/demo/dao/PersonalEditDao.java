package es.demo.dao;

import es.demo.domain.PersonalEdit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalEditDao extends JpaRepository<PersonalEdit, Integer> {

}
