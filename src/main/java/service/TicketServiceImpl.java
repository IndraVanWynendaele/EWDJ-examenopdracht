package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Game;
import domain.MyUser;
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
	public void buyTicket(Ticket ticket, Game game, MyUser user) {
		List<Ticket> existingTickets = tr.findByGameAndUser(game, user);
	    if (!existingTickets.isEmpty()) {
	        Ticket existingTicket = existingTickets.get(0);
	        existingTicket.setAmount(existingTicket.getAmount() + ticket.getAmount());
	        game.setAmountLeft(game.getAmountLeft() - ticket.getAmount());
	        gr.save(game);
	        tr.save(existingTicket);
	        tr.delete(ticket);
	    } else {
	        game.addTicket(ticket);
	        game.setAmountLeft(game.getAmountLeft() - ticket.getAmount());
	        user.addTicket(ticket);
	        tr.save(ticket);
	        gr.save(game);
	        ur.save(user);
	    }
	}

}
