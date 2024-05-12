package validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import service.TicketServiceImpl;

public class TicketValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return TicketServiceImpl.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		TicketServiceImpl ticketService = (TicketServiceImpl) target;
		
		if (ticketService.getAmount() > ticketService.getGame().getAmountAvailable()) {
			errors.rejectValue("amount", "amount.validation.notenough");
		}
		
		if (ticketService.getUser().getTickets().size() + ticketService.getAmount() > 100) {
			errors.rejectValue("amount", "amount.validation.toomany");
		}
		
		if (ticketService.getAmount() > 20) {
			errors.rejectValue("amount", "amount.validation.toobig");
		}
        
        if (ticketService.getAmount() <= 0) {
            errors.rejectValue("amount", "amount.validation.notnull");
        }
	}

}
