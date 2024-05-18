package exceptions;

public class SportNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SportNotFoundException(long sportId) {
		super(String.format("Couldn't find sport with id %d", sportId));
	}
	
}
