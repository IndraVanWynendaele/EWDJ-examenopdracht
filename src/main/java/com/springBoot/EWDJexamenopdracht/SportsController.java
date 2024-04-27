package com.springBoot.EWDJexamenopdracht;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sports")
public class SportsController {

	@GetMapping
	public String showSportsPage() {
		return "sportsTable";
	}
	
}
