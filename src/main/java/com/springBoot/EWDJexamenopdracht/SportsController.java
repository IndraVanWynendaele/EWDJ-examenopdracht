package com.springBoot.EWDJexamenopdracht;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import domain.Sport;
import jakarta.validation.Valid;
import repository.DisciplineRepository;
import repository.GameRepository;
import repository.LocationRepository;
import repository.SportRepository;
import repository.UserRepository;
import service.TicketService;
import service.TicketServiceImpl;
import validator.GameValidator;
import validator.TicketValidator;

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
	@Autowired
	private TicketValidator tv;
	@Autowired
	private TicketService ts;
    
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
	
	@GetMapping(value = "/{sportId}/games/add")
	public String showAddGamePage(@PathVariable long sportId, Model model) {
		Optional<Sport> optionalSport = sr.findById(sportId);
		
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
	
	@PostMapping(value = "/{sportId}/games/add")
	public String onSubmit(@PathVariable long sportId, @Valid Game game, BindingResult result, Model model) {
		Optional<Sport> optionalSport = sr.findById(sportId);
		
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
		
		return "redirect:/sports/{sportId}/games";
	}
	
	@GetMapping(value = "/{sportId}/games/{gameId}/buy")
	public String showBuyTicketsPage(@PathVariable long sportId, @PathVariable long gameId, Model model) {
		Optional<Sport> optionalSport = sr.findById(sportId);
		Optional<Game> optionalGame = gr.findById(gameId);
		
	    if (!optionalSport.isPresent() || !optionalGame.isPresent()) {
	    	model.addAttribute("sportsList", sr.findAll());
	        return "sportsTable";
	    }
	    
	    model.addAttribute("sport", optionalSport.get());
	    model.addAttribute("game", optionalGame.get());
	    model.addAttribute("ticketService", new TicketServiceImpl());
		return "buyTickets";
	}
	
	@PostMapping(value = "/{sportId}/games/{gameId}/buy")
	public String buyTickets(@RequestParam("amount") int amount, @PathVariable long sportId, @PathVariable long gameId, @ModelAttribute("ticketService") TicketServiceImpl ticketService, Model model, BindingResult result, Principal principal) {	
		ticketService.setAmount(amount);
		tv.validate(ticketService, result);
		
		if (result.hasErrors()) {
			return "buyTickets";
		}
		
		ts.buyTicket(amount, gameId, ur.findByEmail(principal.getName()).getId());
		
		return "redirect:/sports/{sportId}/games";
	}
	
}
