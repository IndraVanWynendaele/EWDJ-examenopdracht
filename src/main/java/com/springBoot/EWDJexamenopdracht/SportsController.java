package com.springBoot.EWDJexamenopdracht;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import repository.SportRepository;

@Controller
@RequestMapping("/sports")
public class SportsController {
	
	@Autowired
	private SportRepository sr;

	@GetMapping
	public String showSportsPage(Model model) {
		model.addAttribute("sportsList", sr.findAll());
		return "sportsTable";
	}
	
}
