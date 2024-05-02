package repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import domain.Location;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {

}
