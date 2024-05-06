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
		Game g1 = new Game(LocalDate.of(2024, 7, 26), LocalTime.of(15, 0), 20.0, 10, 12345, 11345);
		Location l1 = new Location("Stade de France");
		Discipline d1 = new Discipline("Running");
		Discipline d2 = new Discipline("Jumping");
		
		Sport s2 = new Sport("Equestrian");
		Game g2 = new Game(LocalDate.of(2024, 7, 30), LocalTime.of(10, 30), 25.0, 49, 23456, 22456);
		Location l2 = new Location("Villennes-sur-Seine Equestrian Center");
		Discipline d3 = new Discipline("Cross");
		
		Sport s3 = new Sport("Surfing");
	    Game g3 = new Game(LocalDate.of(2024, 7, 31), LocalTime.of(12, 15), 15.0, 1, 34567, 34578);
	    Location l3 = new Location("La Torche, Brittany");
        Discipline d4 = new Discipline("Dressage");
        Discipline d5 = new Discipline("Jumping");
		
        Sport s4 = new Sport("Swimming");
		Game g4 = new Game(LocalDate.of(2024, 8, 8), LocalTime.of(9, 0), 30.0, 23, 98453, 99453);
		Location l4 = new Location("Aquatics Center Georges Vallerey");
		
		Sport s5 = new Sport("Skateboarding");
		Game g5 = new Game(LocalDate.of(2024, 8, 9), LocalTime.of(14, 45), 18.0, 40, 78787, 78900);
		Location l5 = new Location("Le Dome Skatepark");
		Discipline d6 = new Discipline("Street");
		
		Sport s6 = new Sport("Artistic gymnastics");
		Game g6 = new Game(LocalDate.of(2024, 8, 10), LocalTime.of(11, 30), 22.0, 35, 45634, 45345);
		Location l6 = new Location("Palais des Sports Marcel Cerdan");
		Discipline d7 = new Discipline("Floor Exercise");
		
		g1.setSport(s1);
		g2.setSport(s2);
		g3.setSport(s2);
		g4.setSport(s3);
		g5.setSport(s4);
		g6.setSport(s5);
		
		g1.addDiscipline(d1);
		g1.addDiscipline(d2);
		g2.addDiscipline(d3);
		g3.addDiscipline(d4);
		g3.addDiscipline(d5);
		g5.addDiscipline(d6);
		g6.addDiscipline(d7);
		
		g1.setLocation(l1);
		g2.setLocation(l2);
		g3.setLocation(l3);
		g4.setLocation(l4);
		g5.setLocation(l5);
		g6.setLocation(l6);
		
		s1.addLocation(l1);
		s2.addLocation(l2);
		s3.addLocation(l3);
		s4.addLocation(l4);
		s5.addLocation(l5);
		s6.addLocation(l6);
	
		s1.addDiscipline(d1);
		s1.addDiscipline(d2);
		s2.addDiscipline(d3);
		s2.addDiscipline(d4);
		s2.addDiscipline(d5);
		s4.addDiscipline(d6);
		s5.addDiscipline(d7);
		
		s1.addGame(g1);
		s2.addGame(g2);
		s2.addGame(g3);
		s3.addGame(g4);
		s4.addGame(g5);
		s5.addGame(g6);
		
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

		sr.save(s1);
		sr.save(s2);
		sr.save(s3);
		sr.save(s4);
		sr.save(s5);
		sr.save(s6);
		
		gr.save(g1);
		gr.save(g2);
		gr.save(g3);
		gr.save(g4);
		gr.save(g5);
		gr.save(g6);
		
		d1.setSport(s1);
		d2.setSport(s1);
		d3.setSport(s2);
		d4.setSport(s2);
		d5.setSport(s2);
		d6.setSport(s4);
		d7.setSport(s5);
		
		dr.save(d1);
		dr.save(d2);
		dr.save(d3);
		dr.save(d4);
		dr.save(d5);
		dr.save(d6);
		dr.save(d7);
	}

}
