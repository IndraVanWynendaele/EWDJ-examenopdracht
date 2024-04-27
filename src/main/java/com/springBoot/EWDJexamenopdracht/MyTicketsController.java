package com.springBoot.EWDJexamenopdracht;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tickets")
public class MyTicketsController {

	@GetMapping
	public String showMyTicketsPage() {
		return "myTickets";
	}
	
}
