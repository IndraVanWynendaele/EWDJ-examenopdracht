package validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import domain.Game;

public class GameValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Game.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Game game = (Game) target;
		String olnronestring = Integer.toString(game.getOlympicNrOne());
		
		if (olnronestring.length() != 5) {
			errors.rejectValue("olympicNrOne", "olympicNrOne.validation.length");
			// TODO 5 niet meer hardcoden!!!! in i18n
		}
		
		if (olnronestring.charAt(0) == '0') {
			errors.rejectValue("olympicNrOne", "olympicNrOne.validation.firstDigit");
			// TODO 0 niet meer hardcoden in properties
		}
		
		if (olnronestring.charAt(0) == olnronestring.charAt(olnronestring.length() - 1)) {
			errors.rejectValue("olympicNrOne", "olympicNrOne.validation.firstAndLast");
		}
		
		if (game.getOlympicNrTwo() > game.getOlympicNrOne() + 1000 || game.getOlympicNrTwo() < game.getOlympicNrOne() - 1000) {
			errors.rejectValue("olympicNrTwo", "olympicNrTwo.validation.range");
			// TODO + 1000 and - 1000 niet meer hardcoden"
		}
	}

}
