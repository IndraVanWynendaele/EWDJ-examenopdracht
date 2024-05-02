package com.springBoot.EWDJexamenopdracht;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import domain.Game;
import domain.Sport;
import repository.GameRepository;
import repository.SportRepository;

@Component
public class InitDataConfig implements CommandLineRunner {

	@Autowired
	private SportRepository sr;
	@Autowired
	private GameRepository gr;
	
	@Override
	public void run(String... args) throws Exception {
		Sport s1 = new Sport("Athletics");
		Sport s2 = new Sport("Equestrian");
		Sport s3 = new Sport("Surfing");
		Sport s4 = new Sport("Swimming");
		Sport s5 = new Sport("Skateboarding");
		Sport s6 = new Sport("Artistic gymnastics");
		
		Game g1 = new Game(LocalDate.of(2024, 5, 5), LocalTime.of(10, 0), "Stadium A", "Athletics", 20.0, 100);
        Game g2 = new Game(LocalDate.of(2024, 5, 6), LocalTime.of(15, 30), "Arena B", "Equestrian", 25.0, 80);
        Game g3 = new Game(LocalDate.of(2024, 5, 7), LocalTime.of(12, 15), "Beach C", "Surfing", 15.0, 50);
        Game g4 = new Game(LocalDate.of(2024, 5, 8), LocalTime.of(9, 0), "Pool D", "Swimming", 30.0, 120);
        Game g5 = new Game(LocalDate.of(2024, 5, 9), LocalTime.of(14, 45), "Skate Park E", "Skateboarding", 18.0, 70);
        Game g6 = new Game(LocalDate.of(2024, 5, 10), LocalTime.of(11, 30), "Gym F", "Artistic gymnastics", 22.0, 90);
        Game g7 = new Game(LocalDate.of(2024, 5, 11), LocalTime.of(16, 20), "Stadium G", "Athletics", 20.0, 100);
		
		s1.addGame(g1);
		s2.addGame(g2);
		s2.addGame(g3);
		s3.addGame(g4);
		s4.addGame(g5);
		s5.addGame(g6);
		
		gr.save(g1);
		gr.save(g2);
		gr.save(g3);
		gr.save(g4);
		gr.save(g5);
		gr.save(g6);
		gr.save(g7);

		sr.save(s1);
		sr.save(s2);
		sr.save(s3);
		sr.save(s4);
		sr.save(s5);
		sr.save(s6);
	}

}
