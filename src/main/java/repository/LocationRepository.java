package repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import domain.Location;
import domain.Sport;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {

//	List<Location> findBySport(Sport sport);
	
}
