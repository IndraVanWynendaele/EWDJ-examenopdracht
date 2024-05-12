package com.springBoot.EWDJexamenopdracht;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import repository.TicketRepository;
import repository.UserRepository;

@Controller
@RequestMapping("/myTickets")
public class MyTicketsController {
	
	@Autowired
	private UserRepository ur;
	@Autowired
	private TicketRepository tr;
	
    @ModelAttribute("email")
    public String username(Principal principal) {
        return principal.getName();
    }
    
    @ModelAttribute("role")
    public String role(Principal principal) {
        return ur.findByEmail(principal.getName()).getRole().toString();
    }

	@GetMapping
	public String showMyTicketsPage(Model model, Principal principal) {
	    model.addAttribute("tickets", tr.findByUserOrderBySportAscDateDesc(ur.findByEmail(principal.getName())));
		return "myTickets";
	}	
}
