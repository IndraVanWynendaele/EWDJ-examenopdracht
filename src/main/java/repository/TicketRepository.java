package repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import domain.Ticket;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Integer> {

//	List<Ticket> findTickets(long userId);
	
}
