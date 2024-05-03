package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Location;
import domain.Sport;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

	List<Location> findBySports(Sport sport);

}
