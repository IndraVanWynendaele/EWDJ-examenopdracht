package com.springBoot.EWDJexamenopdracht;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import domain.Game;
import domain.Role;
import domain.Sport;
import jakarta.validation.Valid;
import repository.DisciplineRepository;
import repository.GameRepository;
import repository.LocationRepository;
import repository.SportRepository;
import repository.UserRepository;
import validator.GameValidator;

@Controller
@RequestMapping("/sports")
public class SportsController {
	
	@Autowired
	private SportRepository sr;
	@Autowired
	private GameRepository gr;
	@Autowired
	private LocationRepository lr;
	@Autowired 
	private DisciplineRepository dr;
	@Autowired
	private UserRepository ur;
	@Autowired
	private GameValidator gv;
    
    @ModelAttribute("email")
    public String username(Principal principal) {
        return principal.getName();
    }
    
    @ModelAttribute("role")
    public String role(Principal principal) {
        return ur.findByEmail(principal.getName()).getRole().toString();
    }
	
	@GetMapping
	public String showSportsPage(Model model, Principal principal) {
		model.addAttribute("role", ur.findByEmail(principal.getName()).getRole());
		model.addAttribute("sportsList", sr.findAll());
		return "sportsTable";
	}
	
	@GetMapping(value = "/{id}/games")
	public String showGamesPage(@PathVariable long id, Model model) {
		Optional<Sport> optionalSport = sr.findById(id);
		if (!optionalSport.isPresent()) {
			model.addAttribute("sportsList", sr.findAll());
			return "sportsTable";
		}
		
		model.addAttribute("sport", optionalSport.get());
		model.addAttribute("games", gr.findBySportOrderByDateAscTimeAsc(optionalSport.get()));
		
		return "gamesTable";
	}
	
	@GetMapping(value = "/{id}/games/add")
	public String showAddGamePage(@PathVariable long id, Model model) {
		Optional<Sport> optionalSport = sr.findById(id);
		
		if (!optionalSport.isPresent()) {
			model.addAttribute("sportsList", sr.findAll());
			return "sportsTable";
		}
		
		model.addAttribute("game", new Game());
		model.addAttribute("sport", optionalSport.get());
		model.addAttribute("disciplines", dr.findBySport(optionalSport.get()));
		model.addAttribute("locations", lr.findBySports(optionalSport.get()));
		
		return "newGame";
	}
	
	@PostMapping(value = "/{id}/games/add")
	public String onSubmit(@RequestParam("id") Long id, @Valid Game game, BindingResult result, Model model) {
		Optional<Sport> optionalSport = sr.findById(id);
		
	    if (!optionalSport.isPresent()) {
	    	model.addAttribute("sportsList", sr.findAll());
	        return "sportsTable";
	    }
	    
	    Sport sport = optionalSport.get();
	    model.addAttribute("sport", sport);
	    model.addAttribute("disciplines", dr.findBySport(sport));
		model.addAttribute("locations", lr.findBySports(sport));
		
		gv.validate(game, result);
		
		if (result.hasErrors()) {
			return "newGame";
		}
		
		sport.addGame(game);
		game.setSport(sport);
		gr.save(game);
		
		return "redirect:/sports/{id}/games";
	}
	
}
