package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long>{

	@Query("SELECT t.game, COUNT(t) FROM Ticket t WHERE t.user.id = :userId GROUP BY t.game")
    List<Object[]> countTicketsGroupedByGame(Long userId);
	
}
