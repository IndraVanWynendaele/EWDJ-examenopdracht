package service;

import domain.Game;
import domain.MyUser;
import domain.Ticket;

public interface TicketService {

	public void buyTicket(Ticket ticket, Game game, MyUser user);
	
}
