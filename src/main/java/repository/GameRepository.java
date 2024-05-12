package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Game;
import domain.Sport;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

	List<Game> findBySportOrderByDateAscTimeAsc(Sport s);
}
