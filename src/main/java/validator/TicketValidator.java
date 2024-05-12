package validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import domain.Ticket;

public class TicketValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Ticket.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		Ticket ticket = (Ticket) target;
		
		if (ticket.getUser().getTickets().stream().mapToInt(Ticket::getAmount).sum() + ticket.getAmount() > 100) {
			errors.rejectValue("amount", "amount.validation.toomany");
			return;
		}
		
		if (ticket.getAmount() > ticket.getGame().getAmountLeft()) {
			errors.rejectValue("amount", "amount.validation.notenough");
			return;
		}
        
        if (ticket.getAmount() <= 0) {
            errors.rejectValue("amount", "validation.notzero");
        }
        
        if (ticket.getAmount() > 20) {
        	errors.rejectValue("amount", "amount.validation.toobig");
        }
	}

}
