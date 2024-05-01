package com.springBoot.EWDJexamenopdracht;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import domain.Sport;
import repository.SportRepository;

@Component
public class InitDataConfig implements CommandLineRunner {

	@Autowired
	private SportRepository sr;
	
	@Override
	public void run(String... args) throws Exception {
		sr.save(new Sport("Athletics"));
		sr.save(new Sport("Equestrian"));
		sr.save(new Sport("Surfing"));
		sr.save(new Sport("Swimming"));
		sr.save(new Sport("Skateboarding"));
		sr.save(new Sport("Artistic gymnastics"));
	}

}
