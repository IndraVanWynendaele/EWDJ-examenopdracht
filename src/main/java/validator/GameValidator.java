package validator;

import java.time.LocalDate;
import java.time.LocalTime;

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
			errors.rejectValue("olympicNrOne", "olympicNrOne.validation.length", new Object[] {"5"}, null);
		}
		
		if (olnronestring.charAt(0) == '0') {
			errors.rejectValue("olympicNrOne", "olympicNrOne.validation.firstDigit", new Object[] {"0"}, null);
		}
		
		if (olnronestring.charAt(0) == olnronestring.charAt(olnronestring.length() - 1)) {
			errors.rejectValue("olympicNrOne", "olympicNrOne.validation.firstAndLast");
		}
		
		
		if (game.getOlympicNrTwo() > game.getOlympicNrOne() + 1000 || game.getOlympicNrTwo() < game.getOlympicNrOne() - 1000) {
			errors.rejectValue("olympicNrTwo", "olympicNrTwo.validation.range", new Object[] {"1000"}, null);
		}
		
		if (game.getDate().isBefore(LocalDate.of(2024, 7, 26)) || game.getDate().isAfter(LocalDate.of(2024, 8, 11))) {
			errors.rejectValue("date", "date.validation.range", new Object[] {"26/07/2024", "11/08/2024"}, null);
		}
		
		if (game.getTime().isBefore(LocalTime.of(8, 0))) {
			errors.rejectValue("time", "time.validation.before", new Object[] {"08:00"}, null);
		}
		
		if (game.getDisciplines().size() > 2) {
			errors.rejectValue("disciplines", "disciplines.validation.size", new Object[] {"0", "2"}, null);
		}
		
		if (game.getDisciplines().size() == 2 && game.getDisciplines().getFirst().getId() == game.getDisciplines().getLast().getId()) {
			errors.rejectValue("disciplines", "disciplines.validation.equal");
		}
	}

}
