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
import domain.Sport;
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
	public int getAvailableTicketsForGame(@PathVariable("sportId") long sportId, @PathVariable("gameId") long gameId) {
		Optional<Game> g = gr.findById(gameId);
		if (g.isPresent()) {
			return g.get().getAmountLeft();
		} else {
			throw new GameNotFoundException(gameId);
		}
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
