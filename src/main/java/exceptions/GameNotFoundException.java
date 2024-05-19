package exceptions;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class GameNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public GameNotFoundException(long gameId) {
		super(MessageFormat.format(ResourceBundle.getBundle("i18n.messages").getString("game.not.found"), gameId));
	}
	
}
