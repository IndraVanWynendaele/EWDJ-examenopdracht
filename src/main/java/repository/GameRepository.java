package repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import domain.Game;

@Repository
public interface GameRepository extends CrudRepository<Game, Integer> {

//	Game findByGameId(long id);
//	List<Game> findGames();

}
