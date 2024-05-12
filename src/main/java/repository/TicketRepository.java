package repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import domain.Game;
import domain.MyUser;
import domain.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long>{

	@Query("SELECT t FROM Ticket t WHERE t.user = :user ORDER BY t.game.sport ASC, t.game.date ASC")
    List<Ticket> findByUserOrderBySportAscDateAsc(@Param("user") MyUser user);
	
	Optional<Ticket> findByGameAndUser(Game game, MyUser user);
	
}
