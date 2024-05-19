package perform;

import org.springframework.web.reactive.function.client.WebClient;

import domain.Game;
import dto.GameDTO;
import reactor.core.publisher.Mono;

public class PerformRest {
	
	private final String SERVER_URI = "http://localhost:8080/rest";
	private WebClient webClient = WebClient.create();
	
	public PerformRest() throws Exception {
		System.out.println("\n------- GET AMOUNT OF TICKETS LEFT GAME ------- ");
		getTicketsLeft(1, 1);
		System.out.println("\n------- GET GAMES LIST BY SPORT ------- ");
		getGamesBySport(3);

	}

	private void getTicketsLeft(long sportId, long gameId) {
		String uri = SERVER_URI + "/sports/" + sportId + "/games/" + gameId + "/available";
		webClient.get().uri(uri).retrieve().bodyToFlux(GameDTO.class).doOnNext(this::printAmount).blockLast();
	}
	
	private void getGamesBySport(long sportId) {
		String uri = SERVER_URI + "/sports/" + sportId + "/games";
		webClient.get().uri(uri).retrieve().bodyToFlux(Game.class).flatMap(game -> {
			printGameInfo(game);
			return Mono.empty();
		}).blockLast();
	}
	
	private void printGameInfo(Game game) {
		if (game != null) {
			System.out.printf("ID = %d, date = %s, time = %s, price = %.2f, "
					+ "amount of tickets available = %d, amount of tickets left = %d, "
					+ "olympic number one = %d, olympic number two = %d"
					+ "sport = %s, location = %s%n",
					game.getId(), game.getDate().toString(), game.getTime().toString(), 
					game.getPrice(), game.getAmountAvailable(), game.getAmountLeft(),
					game.getOlympicNrOne(), game.getOlympicNrTwo(), game.getSport().getName(), game.getLocation().getName());
		} else {
			System.out.println("Game is null");
		}
	}

	private void printAmount(GameDTO dto) {
		if (dto != null) {
			System.out.printf("ID = %d, amount of tickets left = %d%n", dto.id(), dto.amountLeft());
		} else {
			System.out.println("Game is null");
		}
		
	}
}
