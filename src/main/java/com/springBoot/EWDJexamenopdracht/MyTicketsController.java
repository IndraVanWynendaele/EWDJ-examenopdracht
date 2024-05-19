package com.springBoot.EWDJexamenopdracht;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.MyUser;
import domain.Role;
import repository.TicketRepository;
import repository.UserRepository;

@Controller
@RequestMapping("/myTickets")
public class MyTicketsController {
	
	@Autowired
	private UserRepository ur;
	@Autowired
	private TicketRepository tr;
	
	@ModelAttribute("user")
    public MyUser user(Principal principal) {
        return ur.findByEmail(principal.getName());
    }
	
	@ModelAttribute("email")
    public String email(Principal principal) {
    	return ur.findByEmail(principal.getName()).getEmail();
    }
    
    @ModelAttribute("role")
    public Role role(Principal principal) {
    	return ur.findByEmail(principal.getName()).getRole();
    }

	@GetMapping
	public String showMyTicketsPage(Model model, Principal principal) {
	    model.addAttribute("tickets", tr.findByUserOrderBySportAscDateAsc(ur.findByEmail(principal.getName())));
		return "myTickets";
	}	
}
