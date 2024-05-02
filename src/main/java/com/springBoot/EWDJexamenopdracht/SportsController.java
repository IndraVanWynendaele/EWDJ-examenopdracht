package com.springBoot.EWDJexamenopdracht;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import domain.Game;
import domain.Sport;
import repository.GameRepository;
import repository.SportRepository;

@Controller
@RequestMapping("/sports")
public class SportsController {
	
	@Autowired
	private SportRepository sr;
	@Autowired
	private GameRepository gr;

	@GetMapping
	public String showSportsPage(Model model) {
		model.addAttribute("sportsList", sr.findAll());
		return "sportsTable";
	}
	
	@GetMapping(value = "/{id}/games")
	public String showGamesPage(@PathVariable long id, Model model) {
		Optional<Sport> optionalSport = sr.findById(id);
		if (!optionalSport.isPresent()) {
			return "sportsTable";
		}
		model.addAttribute("sport", optionalSport.get());
		model.addAttribute("games", gr.findByOrderByDateAscTimeAsc());
		return "gamesTable";
	}
	
}
