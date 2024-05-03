package repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import domain.Discipline;
import domain.Sport;

@Repository
public interface DisciplineRepository extends CrudRepository<Discipline, Long> {

	// TODO findall per sport
	List<Discipline> findBySport(Sport sport);
	
}
