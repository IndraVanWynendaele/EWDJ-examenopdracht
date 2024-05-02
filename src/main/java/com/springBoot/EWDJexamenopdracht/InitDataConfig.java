package com.springBoot.EWDJexamenopdracht;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import domain.Discipline;
import domain.Game;
import domain.Location;
import domain.Sport;
import repository.DisciplineRepository;
import repository.GameRepository;
import repository.LocationRepository;
import repository.SportRepository;

@Component
public class InitDataConfig implements CommandLineRunner {

	@Autowired
	private SportRepository sr;
	@Autowired
	private GameRepository gr;
	@Autowired 
	private LocationRepository lr;
	@Autowired 
	private DisciplineRepository dr;
	
	@Override
	public void run(String... args) throws Exception {
		
		Sport s1 = new Sport("Athletics");
		Game g1 = new Game(LocalDate.of(2024, 5, 5), LocalTime.of(10, 0), 20.0, 100);
		Location l1 = new Location("Stade de France");
		Discipline d1 = new Discipline("Running");
		Discipline d2 = new Discipline("Jumping");
		
		Sport s2 = new Sport("Equestrian");
		Game g2 = new Game(LocalDate.of(2024, 5, 6), LocalTime.of(15, 30), 25.0, 80);
		Location l2 = new Location("Villennes-sur-Seine Equestrian Center");
		Discipline d3 = new Discipline("Cross");
	    Game g3 = new Game(LocalDate.of(2024, 5, 7), LocalTime.of(12, 15), 15.0, 50);
	    Location l3 = new Location("La Torche, Brittany");
        Discipline d4 = new Discipline("Dressage");
        Discipline d5 = new Discipline("Jumping");
		
		Sport s3 = new Sport("Surfing");
		Game g4 = new Game(LocalDate.of(2024, 5, 8), LocalTime.of(9, 0), 30.0, 120);
		Location l4 = new Location("Aquatics Center Georges Vallerey");
		
		Sport s4 = new Sport("Swimming");
		Game g5 = new Game(LocalDate.of(2024, 5, 9), LocalTime.of(14, 45), 18.0, 70);
		Location l5 = new Location("Le Dome Skatepark");
		Discipline d6 = new Discipline("Street");
		
		
		Sport s5 = new Sport("Skateboarding");
		Game g6 = new Game(LocalDate.of(2024, 5, 10), LocalTime.of(11, 30), 22.0, 90);
		Location l6 = new Location("Palais des Sports Marcel Cerdan");
		Discipline d7 = new Discipline("Floor Exercise");
		
		Sport s6 = new Sport("Artistic gymnastics");
		
		s1.addGame(g1);
		s2.addGame(g2);
		s2.addGame(g3);
		s3.addGame(g4);
		s4.addGame(g5);
		s5.addGame(g6);
		
		g1.setLocation(l1);
		g2.setLocation(l2);
		g3.setLocation(l3);
		g4.setLocation(l4);
		g5.setLocation(l5);
		g6.setLocation(l6);
		
		g1.addDiscipline(d1);
		g1.addDiscipline(d2);
		g2.addDiscipline(d3);
		g3.addDiscipline(d4);
		g3.addDiscipline(d5);
		g5.addDiscipline(d6);
		g6.addDiscipline(d7);
		
		lr.save(l1);
		lr.save(l2);
		lr.save(l3);
		lr.save(l4);
		lr.save(l5);
		lr.save(l6);
		
		dr.save(d1);
		dr.save(d2);
		dr.save(d3);
		dr.save(d4);
		dr.save(d5);
		dr.save(d6);
		dr.save(d7);
		
		gr.save(g1);
		gr.save(g2);
		gr.save(g3);
		gr.save(g4);
		gr.save(g5);
		gr.save(g6);

		sr.save(s1);
		sr.save(s2);
		sr.save(s3);
		sr.save(s4);
		sr.save(s5);
		sr.save(s6);
	}

}
