package validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import service.TicketService;
import service.TicketServiceImpl;

public class TicketValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return TicketServiceImpl.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		TicketServiceImpl ticketService = (TicketServiceImpl) target;
        // Validate amount
        if (ticketService.getAmount() <= 0) {
            errors.rejectValue("amount", "negativeOrZero", "Amount must be greater than zero");
        }
	}

}
