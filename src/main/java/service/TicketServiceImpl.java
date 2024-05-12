package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Ticket;
import lombok.NoArgsConstructor;
import repository.GameRepository;
import repository.TicketRepository;
import repository.UserRepository;

@Service
@NoArgsConstructor
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepository tr;
	@Autowired
	private GameRepository gr;
	@Autowired
	private UserRepository ur;

	@Override
	public void buyTicket(Ticket ticket) {
		// checken of er al tickets voor die game zijn -> amount update
		// anders nieuwe maken
	}

}
