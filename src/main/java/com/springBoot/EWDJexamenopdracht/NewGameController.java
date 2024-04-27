package com.springBoot.EWDJexamenopdracht;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/games/add")
public class NewGameController {
	
	@GetMapping
	public String showSportsPage() {
		return "newGame";
	}
	
	@PostMapping
	public String onSubmit() {
		return "redirect:/games";
	}
}
