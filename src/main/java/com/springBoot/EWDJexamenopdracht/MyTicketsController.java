package com.springBoot.EWDJexamenopdracht;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.Role;
import repository.UserRepository;

@Controller
@RequestMapping("/myTickets")
public class MyTicketsController {
	
	@Autowired
	private UserRepository ur;
	
	@ModelAttribute("email")
    public String username(Principal principal) {
        return principal.getName();
    }
    
    @ModelAttribute("role")
    public Role role(Principal principal) {
        return ur.findByEmail(principal.getName()).getRole();
    }

	@GetMapping
	public String showMyTicketsPage() {
		return "myTickets";
	}	
}
