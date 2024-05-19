package com.springBoot.EWDJexamenopdracht;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import domain.Game;
import dto.GameDTO;
import exceptions.GameNotFoundException;
import exceptions.SportNotFoundException;
import repository.GameRepository;
import repository.SportRepository;

@RestController
@RequestMapping(value = "/rest")
public class SportsRestController {
	
	@Autowired
	private SportRepository sr;
	@Autowired
	private GameRepository gr;
	
	@GetMapping("/sports/{sportId}/games/{gameId}/available")
	public GameDTO getAvailableTicketsForGame(@PathVariable("sportId") long sportId, @PathVariable("gameId") long gameId) {
		Optional<Game> gOptional = gr.findById(gameId);
		if (!gOptional.isPresent()) {
			throw new GameNotFoundException(gameId);
		} 
		Game g = gOptional.get();
		return new GameDTO(g.getId(), g.getAmountLeft());
	}	
	
	@GetMapping("/sports/{sportId}/games")
	public List<Game> getGamesBySport(@PathVariable("sportId") long sportId) {
		List<Game> games = new ArrayList<>();
		try {
			games = gr.findBySportOrderByDateAscTimeAsc(sr.findById(sportId).get());
		} catch (NoSuchElementException ex) {
			throw new SportNotFoundException(sportId);
		}
		return games;
	}
	
}
