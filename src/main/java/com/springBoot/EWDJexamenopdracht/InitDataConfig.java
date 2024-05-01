package com.springBoot.EWDJexamenopdracht;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import domain.Discipline;
import domain.Game;
import domain.Location;
import repository.DisciplineRepository;
import repository.GameRepository;
import repository.LocationRepository;
import repository.TicketRepository;

public class InitDataConfig implements CommandLineRunner {
	
	@Autowired DisciplineRepository dr;
	@Autowired GameRepository gr;
	@Autowired LocationRepository lr;
	@Autowired TicketRepository tr;

	@Override
	public void run(String... args) throws Exception {
		Discipline d1 = new Discipline(1, "Men's 100m");
		Discipline d2 = new Discipline(2, "Women's 100m");
		Discipline d3 = new Discipline(3, "Women's Long Jump");
		Discipline d4 = new Discipline(4, "Men's Long Jump");
		Discipline d5 = new Discipline(5, "Women's 400m");
		
		dr.save(d1);
		dr.save(d2);
		dr.save(d3);
		dr.save(d4);
		dr.save(d5);
		
		Location l1 = new Location(1, "Stade de France");
		Location l2 = new Location(2, "Parc des Princes");
		Location l3 = new Location(3, "La Beaujoire Stadium");
		Location l4 = new Location(4, "Château de Versailles");
		Location l5 = new Location(5, "Teahupo'o, Tahiti");
		Location l6 = new Location(6, "Bercy Arena");
		
		lr.save(l1);
		lr.save(l2);
		lr.save(l3);
		lr.save(l4);
		lr.save(l5);
		lr.save(l6);
		
		LocalDate date1 = LocalDate.of(2024, 7, 15);
        LocalDate date2 = LocalDate.of(2024, 8, 5);
        LocalDate date3 = LocalDate.of(2024, 8, 21);
        LocalDate date4 = LocalDate.of(2024, 9, 8);
        LocalDate date5 = LocalDate.of(2024, 7, 29);
        LocalDate date6 = LocalDate.of(2024, 9, 3);
        
        LocalTime time1 = LocalTime.of(8, 0, 0);
        LocalTime time2 = LocalTime.of(10, 30, 0);
        LocalTime time3 = LocalTime.of(13, 15, 0);
        LocalTime time4 = LocalTime.of(15, 45, 0);
        LocalTime time5 = LocalTime.of(18, 0, 0);
        LocalTime time6 = LocalTime.of(20, 30, 0);
        
        List<Discipline> disciplines = new ArrayList<>();
        disciplines.add(d1);
		Game g1 = new Game(1, date1, time1, l1, disciplines, 50.00, 49, 12345, 11346);
		disciplines.add(d2);
		Game g2 = new Game(2, date2, time2, l2, disciplines, 50.00, 49, 12345, 11346);
		disciplines.removeAll(disciplines);
		disciplines.add(d5);
		disciplines.add(d3);
		Game g3 = new Game(3, date3, time3, l3, disciplines, 50.00, 49, 12345, 11346);
		disciplines.removeAll(disciplines);
		disciplines.add(d2);
		Game g4 = new Game(4, date4, time4, l4, disciplines, 50.00, 49, 12345, 11346);
		disciplines.removeAll(disciplines);
		disciplines.add(d4);
		Game g5 = new Game(5, date5, time5, l5, disciplines, 50.00, 49, 12345, 11346);
		disciplines.removeAll(disciplines);
		disciplines.add(d1);
		disciplines.add(d5);
		Game g6 = new Game(6, date6, time6, l6, disciplines, 50.00, 49, 12345, 11346);

		gr.save(g1);
		gr.save(g2);
		gr.save(g3);
		gr.save(g4);
		gr.save(g5);
		gr.save(g6);
	}

}
