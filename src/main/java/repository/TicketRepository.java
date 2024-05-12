package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import domain.MyUser;
import domain.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long>{

	@Query("SELECT t FROM Ticket t WHERE t.user = :user ORDER BY t.game.sport ASC, t.game.date DESC")
    List<Ticket> findByUserOrderBySportAscDateDesc(@Param("user") MyUser user);
	
}
