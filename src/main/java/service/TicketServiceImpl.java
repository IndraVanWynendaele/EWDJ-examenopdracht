package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Game;
import domain.MyUser;
import domain.Ticket;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import repository.GameRepository;
import repository.TicketRepository;
import repository.UserRepository;

@Service
@Transactional
@NoArgsConstructor
public class TicketServiceImpl implements TicketService {
	
	@Autowired
	private TicketRepository tr;
	@Autowired
    private GameRepository gr;
	@Autowired
    private UserRepository ur;
    @Getter
    private int amount;

	@Override
	public void buyTicket(int amount, long gameId, long userId) {
		Game g = gr.findById(gameId).get();
		MyUser u = ur.findById(userId).get();
		
		for (int i = 0; i < amount; i++) {
            Ticket t = new Ticket();
            t.setGame(g);
            t.setUser(u);
            g.addTicket(t);
            u.addTicket(t);
            
            tr.save(t);
            ur.save(u);
            gr.save(g);
        }
	}

}
