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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import domain.Game;
import domain.MyUser;
import domain.Sport;
import domain.Ticket;
import jakarta.validation.Valid;
import repository.DisciplineRepository;
import repository.GameRepository;
import repository.LocationRepository;
import repository.SportRepository;
import repository.TicketRepository;
import repository.UserRepository;
import service.TicketService;
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
	private TicketRepository tr;
	@Autowired
	private GameValidator gv;
	@Autowired
	private TicketValidator tv;
	@Autowired
	private TicketService ts;
	
    @ModelAttribute("user")
    public MyUser user(Principal principal) {
        return ur.findByEmail(principal.getName());
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
		
		// TODO miss hier ook serice voor gebruiken ipv heir de 3 stappen te doen?		
		sport.addGame(game);
		game.setSport(sport);
		gr.save(game);
		
		return "redirect:/sports/{sportId}/games";
	}
	
	@GetMapping(value = "/{sportId}/games/{gameId}/buy")
	public String showBuyTicketsPage(@PathVariable long sportId, @PathVariable long gameId, Model model, Principal principal) {
		Optional<Game> optionalGame = gr.findById(gameId);
		Optional<Sport> optionalSport = sr.findById(sportId);
		
	    if (!optionalGame.isPresent() || !optionalSport.isPresent()) {
	    	model.addAttribute("sportsList", sr.findAll());
	        return "sportsTable";
	    }
	    
	    Optional<Ticket> ticketOptional = tr.findByGameAndUser(optionalGame.get(), ur.findByEmail(principal.getName()));
	    if (ticketOptional.isPresent()) {
	        Ticket ticket = ticketOptional.get();
	        model.addAttribute("amountOfTickets", ticket.getAmount());
	    } else {
	        model.addAttribute("amountOfTickets", 0); // or any default value you prefer
	    }
	    model.addAttribute("game", optionalGame.get());
	    model.addAttribute("sport", optionalSport.get());
	    model.addAttribute("ticket", new Ticket());
		return "buyTickets";
	}
	
	@PostMapping(value = "/{sportId}/games/{gameId}/buy")
	public String buyTickets(@PathVariable long sportId, @PathVariable long gameId, @Valid Ticket ticket, Model model, BindingResult result, Principal principal, RedirectAttributes redirectAttributes) {	
		Optional<Game> optionalGame = gr.findById(gameId);
		Optional<Sport> optionalSport = sr.findById(sportId);
		
	    if (!optionalGame.isPresent() || !optionalSport.isPresent()) {
	    	model.addAttribute("sportsList", sr.findAll());
	        return "sportsTable";
	    }
	    
	    Game game = optionalGame.get();
	    MyUser user = ur.findByEmail(principal.getName());
	    model.addAttribute("game", game);
	    model.addAttribute("sport", optionalSport.get());
	    
	    ticket.setGame(game);
	    ticket.setUser(user);
		tv.validate(ticket, result);
		
		if (result.hasErrors()) {
			return "buyTickets";
		}
		
		ts.buyTicket(ticket, game, user);
		
		redirectAttributes.addFlashAttribute("amountBought", ticket.getAmount());
		redirectAttributes.addFlashAttribute("game", ticket.getGame());
		
		return "redirect:/sports/{sportId}/games";
	}
	
}
