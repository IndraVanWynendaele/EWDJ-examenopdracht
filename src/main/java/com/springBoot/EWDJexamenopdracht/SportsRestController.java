package com.springBoot.EWDJexamenopdracht;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import domain.Sport;
import jakarta.persistence.EntityNotFoundException;
import repository.SportRepository;

@RestController
@RequestMapping(value = "/rest")
public class SportsRestController {
	
	@Autowired
	private SportRepository sr;
	
	@GetMapping("/sports")
	public List<Sport> getSports() {
		return sr.findAll();
	}

	@GetMapping("/sports/{id}")
	public Sport getSportById(@PathVariable("id") long id) {
		Optional<Sport> s = sr.findById(id);
		if (s.isPresent()) {
			return s.get();
		} else {
			throw new EntityNotFoundException();
		}
	}
	
}
