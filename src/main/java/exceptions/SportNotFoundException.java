package exceptions;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class SportNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SportNotFoundException(long sportId) {
		super(MessageFormat.format(ResourceBundle.getBundle("i18n.messages").getString("sport.not.found"), sportId));
	}
}
