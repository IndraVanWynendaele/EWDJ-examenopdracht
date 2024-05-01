package repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import domain.Discipline;

@Repository
public interface DisciplineRepository extends CrudRepository<Discipline, Integer> {

//	List<Discipline> findDisciplines();
	
}
