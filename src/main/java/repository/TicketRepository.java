package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import domain.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long>{

}
