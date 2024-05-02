package repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import domain.Sport;

@Repository
public interface SportRepository extends CrudRepository<Sport, Long> {

	List<Sport> findAll();
	Optional<Sport> findById(Long id);
}
