package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Discipline;
import domain.Sport;

@Repository
public interface DisciplineRepository extends JpaRepository<Discipline, Long> {

	List<Discipline> findBySport(Sport sport);
	
}
