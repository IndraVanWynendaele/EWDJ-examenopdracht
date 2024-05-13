package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Game;
import domain.Sport;
import lombok.NoArgsConstructor;
import repository.GameRepository;

@Service
@NoArgsConstructor
public class GameServiceImpl implements GameService {

	@Autowired
	private GameRepository gr;
	
	@Override
	public void addGame(Sport sport, Game game) {
		sport.addGame(game);
		game.setSport(sport);
		game.setAmountLeft(game.getAmountAvailable());
		gr.save(game);
	}

}
