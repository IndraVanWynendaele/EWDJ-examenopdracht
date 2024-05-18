package exceptions;

public class GameNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public GameNotFoundException(long gameId) {
		super(String.format("Couldn't find game with id %d", gameId));
	}
	
}
