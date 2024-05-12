	package com.springBoot.EWDJexamenopdracht;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import domain.Discipline;
import domain.Game;
import domain.Location;
import domain.MyUser;
import domain.Role;
import domain.Sport;
import domain.Ticket;
import repository.DisciplineRepository;
import repository.GameRepository;
import repository.LocationRepository;
import repository.SportRepository;
import repository.TicketRepository;
import repository.UserRepository;
import service.TicketService;

@Component
public class InitDataConfig implements CommandLineRunner {
	
	private PasswordEncoder encoder = new BCryptPasswordEncoder();

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
	private TicketService ts;
	
	private MyUser user;
	private MyUser user2;
	private MyUser admin;
	
	@Override
	public void run(String... args) throws Exception {	
		initUsers();		
		initSportAthletics();
		initSportEquestrian();
		initSportSurfing();
		initSportSwimming();
		initSportSkateboarding();
		initSportGymnastics();
	}
	
	private void initUsers() {
		user = new MyUser("user@javaweb.com", encoder.encode("Password"), Role.USER);
		user2 = new MyUser("user2@javaweb.com", encoder.encode("Password"), Role.USER);
		admin = new MyUser("admin@javaweb.com", encoder.encode("Password"), Role.ADMIN);
		
		ur.save(user);
		ur.save(user2);
		ur.save(admin);
	}
	
	private void initSportAthletics() {
		Sport s1 = new Sport("Athletics");
		Game g1 = new Game(LocalDate.of(2024, 7, 26), LocalTime.of(15, 0), 20.0, 10, 10000, 11000);
		Game g11 = new Game(LocalDate.of(2024, 7, 26), LocalTime.of(12, 30), 40.0, 39, 11000, 12000);
		Game g12 = new Game(LocalDate.of(2024, 7, 28), LocalTime.of(15, 0), 149.99, 49, 12000, 13000);
		Game g13 = new Game(LocalDate.of(2024, 7, 29), LocalTime.of(10, 0), 33, 30, 13000, 14000);
		Game g14 = new Game(LocalDate.of(2024, 7, 29), LocalTime.of(9, 0), 24.0, 20, 14000, 15000);
		Game g15 = new Game(LocalDate.of(2024, 8, 1), LocalTime.of(8, 0), 100.0, 40, 15000, 16000);
		Game g16 = new Game(LocalDate.of(2024, 8, 3), LocalTime.of(22, 0), 74.0, 23, 16000, 17000);
		Game g17 = new Game(LocalDate.of(2024, 8, 3), LocalTime.of(17, 45), 62.0, 30, 17000, 18000);
		Location l1 = new Location("Stade de France");
		Location l11 = new Location("Parc des Princes");
		Location l12 = new Location("Stade Charléty");
		Location l13 = new Location("Château de Versailles");
		Location l14 = new Location("Bois de Boulogne");
		Location l15 = new Location("Champ de Mars");
		Discipline d1 = new Discipline("Hurdles 110m");
		Discipline d11 = new Discipline("Long Jump");
		Discipline d12 = new Discipline("Sprints 400m");
		Discipline d13 = new Discipline("Discus Throw");
		Discipline d14 = new Discipline("Hammer Throw");
		Discipline d15 = new Discipline("High Jump");
		Discipline d16 = new Discipline("Sprints 200m");
		
		d1.setSport(s1);
		d11.setSport(s1);
		d12.setSport(s1);
		d13.setSport(s1);
		d14.setSport(s1);
		d15.setSport(s1);
		d16.setSport(s1);
		
		g1.setSport(s1);
		g11.setSport(s1);
		g12.setSport(s1);
		g13.setSport(s1);
		g14.setSport(s1);
		g15.setSport(s1);
		g16.setSport(s1);
		g17.setSport(s1);
		
		g1.addDiscipline(d1);
		g1.addDiscipline(d11);
		g11.addDiscipline(d12);
		g12.addDiscipline(d13);
		g13.addDiscipline(d13);
		g14.addDiscipline(d14);
		g14.addDiscipline(d15);
		g15.addDiscipline(d16);
		g17.addDiscipline(d1);
		g17.addDiscipline(d15);
		
		g1.setLocation(l1);
		g11.setLocation(l11);
		g12.setLocation(l12);
		g13.setLocation(l13);
		g14.setLocation(l14);
		g15.setLocation(l15);
		g16.setLocation(l15);
		g17.setLocation(l1);
		
		s1.addLocation(l1);
		s1.addLocation(l11);
		s1.addLocation(l12);
		s1.addLocation(l13);
		s1.addLocation(l14);
		s1.addLocation(l15);
		
		s1.addDiscipline(d1);
		s1.addDiscipline(d11);
		s1.addDiscipline(d12);
		s1.addDiscipline(d13);
		s1.addDiscipline(d14);
		s1.addDiscipline(d15);
		s1.addDiscipline(d16);
		
		s1.addGame(g1);
		s1.addGame(g12);
		s1.addGame(g13);
		s1.addGame(g14);
		s1.addGame(g15);
		s1.addGame(g16);
		s1.addGame(g17);
		
		lr.save(l1);
		lr.save(l11);
		lr.save(l12);
		lr.save(l13);
		lr.save(l14);
		lr.save(l15);
		
		sr.save(s1);
		
		dr.save(d1);
		dr.save(d11);
		dr.save(d12);
		dr.save(d13);
		dr.save(d14);
		dr.save(d15);
		dr.save(d16);
		
		gr.save(g1);
		gr.save(g11);
		gr.save(g12);
		gr.save(g13);
		gr.save(g14);
		gr.save(g15);
		gr.save(g16);
		gr.save(g17);
		
		Ticket t1 = new Ticket();
		t1.setGame(g1);
		t1.setUser(user);
		t1.setAmount(2);
		Ticket t11 = new Ticket();
		t11.setGame(g17);
		t11.setUser(user);
		t11.setAmount(19);
		Ticket t13 = new Ticket();
		t13.setGame(g17);
		t13.setUser(user2);
		t13.setAmount(7);
		
		ts.buyTicket(t1, g1, user);
		ts.buyTicket(t11, g17, user);
		ts.buyTicket(t13, g17, user2);
//		user.addTicket(t1);
//		user.addTicket(t11);
//		user2.addTicket(t13);
//		g1.addTicket(t1);
//		g17.addTicket(t11);
//		g17.addTicket(t13);
		
//		tr.save(t1);
//		tr.save(t11);
//		tr.save(t13);
//		
//		gr.save(g1);
//		gr.save(g11);
//		gr.save(g12);
//		gr.save(g13);
//		gr.save(g14);
//		gr.save(g15);
//		gr.save(g16);
//		gr.save(g17);
	}
	
	private void initSportEquestrian() {
		Sport s1 = new Sport("Equestrian");
		Game g1 = new Game(LocalDate.of(2024, 7, 26), LocalTime.of(15, 0), 20.0, 10, 20000, 21000);
		Game g11 = new Game(LocalDate.of(2024, 7, 26), LocalTime.of(12, 30), 40.0, 39, 21000, 22000);
		Game g12 = new Game(LocalDate.of(2024, 7, 28), LocalTime.of(15, 0), 149.99, 49, 22000, 23000);
		Game g13 = new Game(LocalDate.of(2024, 7, 29), LocalTime.of(10, 0), 33, 30, 23000, 24000);
		Game g14 = new Game(LocalDate.of(2024, 7, 29), LocalTime.of(9, 0), 24.0, 20, 24000, 25000);
		Game g15 = new Game(LocalDate.of(2024, 8, 1), LocalTime.of(8, 0), 100.0, 40, 25000, 26000);
		Game g16 = new Game(LocalDate.of(2024, 8, 3), LocalTime.of(22, 0), 74.0, 23, 26000, 27000);
		Game g17 = new Game(LocalDate.of(2024, 8, 3), LocalTime.of(17, 45), 62.0, 30, 27000, 28000);
		Location l1 = new Location("Château de Versailles");
		Location l11 = new Location("Bois de Boulogne");
		Location l12 = new Location("Parc Équestre Fédéral de Lamotte-Beuvron");
		Location l13 = new Location("Chantilly");
		Location l14 = new Location("Fontainebleau");
		Location l15 = new Location("Haras de Jardy");
		Discipline d1 = new Discipline("Dressage");
		Discipline d11 = new Discipline("Show Jumping");
		Discipline d12 = new Discipline("Eventing");
		Discipline d13 = new Discipline("Endurance Riding");
		Discipline d14 = new Discipline("Vaulting");
		Discipline d15 = new Discipline("Reining");
		Discipline d16 = new Discipline("Polo");
		
		d1.setSport(s1);
		d11.setSport(s1);
		d12.setSport(s1);
		d13.setSport(s1);
		d14.setSport(s1);
		d15.setSport(s1);
		d16.setSport(s1);
		
		g1.setSport(s1);
		g11.setSport(s1);
		g12.setSport(s1);
		g13.setSport(s1);
		g14.setSport(s1);
		g15.setSport(s1);
		g16.setSport(s1);
		g17.setSport(s1);
		
		g1.addDiscipline(d1);
		g1.addDiscipline(d11);
		g11.addDiscipline(d12);
		g12.addDiscipline(d13);
		g13.addDiscipline(d13);
		g14.addDiscipline(d14);
		g14.addDiscipline(d15);
		g15.addDiscipline(d16);
		g17.addDiscipline(d1);
		g17.addDiscipline(d15);
		
		g1.setLocation(l1);
		g11.setLocation(l11);
		g12.setLocation(l12);
		g13.setLocation(l13);
		g14.setLocation(l14);
		g15.setLocation(l15);
		g16.setLocation(l15);
		g17.setLocation(l1);
		
		s1.addLocation(l1);
		s1.addLocation(l11);
		s1.addLocation(l12);
		s1.addLocation(l13);
		s1.addLocation(l14);
		s1.addLocation(l15);
		
		s1.addDiscipline(d1);
		s1.addDiscipline(d11);
		s1.addDiscipline(d12);
		s1.addDiscipline(d13);
		s1.addDiscipline(d14);
		s1.addDiscipline(d15);
		s1.addDiscipline(d16);
		
		s1.addGame(g1);
		s1.addGame(g12);
		s1.addGame(g13);
		s1.addGame(g14);
		s1.addGame(g15);
		s1.addGame(g16);
		s1.addGame(g17);
		
		lr.save(l1);
		lr.save(l11);
		lr.save(l12);
		lr.save(l13);
		lr.save(l14);
		lr.save(l15);
		
		sr.save(s1);
		
		dr.save(d1);
		dr.save(d11);
		dr.save(d12);
		dr.save(d13);
		dr.save(d14);
		dr.save(d15);
		dr.save(d16);
		
		gr.save(g1);
		gr.save(g11);
		gr.save(g12);
		gr.save(g13);
		gr.save(g14);
		gr.save(g15);
		gr.save(g16);
		gr.save(g17);
	}
	
	private void initSportSurfing() {
		Sport s1 = new Sport("Surfing");
		Game g1 = new Game(LocalDate.of(2024, 7, 26), LocalTime.of(15, 0), 20.0, 10, 30000, 31000);
		Game g11 = new Game(LocalDate.of(2024, 7, 26), LocalTime.of(12, 30), 40.0, 39, 31000, 32000);
		Game g12 = new Game(LocalDate.of(2024, 7, 28), LocalTime.of(15, 0), 149.99, 49, 32000, 33000);
		Game g13 = new Game(LocalDate.of(2024, 7, 29), LocalTime.of(10, 0), 33, 30, 33000, 34000);
		Game g14 = new Game(LocalDate.of(2024, 7, 29), LocalTime.of(9, 0), 24.0, 20, 34000, 35000);
		Game g15 = new Game(LocalDate.of(2024, 8, 1), LocalTime.of(8, 0), 100.0, 40, 35000, 36000);
		Game g16 = new Game(LocalDate.of(2024, 8, 3), LocalTime.of(22, 0), 74.0, 23, 36000, 37000);
		Game g17 = new Game(LocalDate.of(2024, 8, 3), LocalTime.of(17, 45), 62.0, 30, 37000, 38000);
		Location l1 = new Location("Seine River Surfing");
		Location l11 = new Location("La Villette Basin");
		Location l12 = new Location("Aquaboulevard");
		Location l13 = new Location("Saint-Quentin-en-Yvelines Lake");
		Location l14 = new Location("Bois de Boulogne Waterways");
		Location l15 = new Location("Chantilly Castle Lake");
		Discipline d1 = new Discipline("Shortboard");
		Discipline d11 = new Discipline("Longboard");
		Discipline d12 = new Discipline("Big Wave");
		Discipline d13 = new Discipline("Tandem Surfing");
		Discipline d14 = new Discipline("Adaptive Surfing");
		Discipline d15 = new Discipline("Stand-Up Paddle Surfing");
		Discipline d16 = new Discipline("Bodyboarding");
		
		d1.setSport(s1);
		d11.setSport(s1);
		d12.setSport(s1);
		d13.setSport(s1);
		d14.setSport(s1);
		d15.setSport(s1);
		d16.setSport(s1);
		
		g1.setSport(s1);
		g11.setSport(s1);
		g12.setSport(s1);
		g13.setSport(s1);
		g14.setSport(s1);
		g15.setSport(s1);
		g16.setSport(s1);
		g17.setSport(s1);
		
		g1.addDiscipline(d1);
		g1.addDiscipline(d11);
		g11.addDiscipline(d12);
		g12.addDiscipline(d13);
		g13.addDiscipline(d13);
		g14.addDiscipline(d14);
		g14.addDiscipline(d15);
		g15.addDiscipline(d16);
		g17.addDiscipline(d1);
		g17.addDiscipline(d15);
		
		g1.setLocation(l1);
		g11.setLocation(l11);
		g12.setLocation(l12);
		g13.setLocation(l13);
		g14.setLocation(l14);
		g15.setLocation(l15);
		g16.setLocation(l15);
		g17.setLocation(l1);
		
		s1.addLocation(l1);
		s1.addLocation(l11);
		s1.addLocation(l12);
		s1.addLocation(l13);
		s1.addLocation(l14);
		s1.addLocation(l15);
		
		s1.addDiscipline(d1);
		s1.addDiscipline(d11);
		s1.addDiscipline(d12);
		s1.addDiscipline(d13);
		s1.addDiscipline(d14);
		s1.addDiscipline(d15);
		s1.addDiscipline(d16);
		
		s1.addGame(g1);
		s1.addGame(g12);
		s1.addGame(g13);
		s1.addGame(g14);
		s1.addGame(g15);
		s1.addGame(g16);
		s1.addGame(g17);
		
		lr.save(l1);
		lr.save(l11);
		lr.save(l12);
		lr.save(l13);
		lr.save(l14);
		lr.save(l15);
		
		sr.save(s1);
		
		dr.save(d1);
		dr.save(d11);
		dr.save(d12);
		dr.save(d13);
		dr.save(d14);
		dr.save(d15);
		dr.save(d16);
		
		gr.save(g1);
		gr.save(g11);
		gr.save(g12);
		gr.save(g13);
		gr.save(g14);
		gr.save(g15);
		gr.save(g16);
		gr.save(g17);
	}
	
	private void initSportSwimming() {
		Sport s1 = new Sport("Swimming");
		Game g1 = new Game(LocalDate.of(2024, 7, 26), LocalTime.of(15, 0), 20.0, 10, 40000, 41000);
		Game g11 = new Game(LocalDate.of(2024, 7, 26), LocalTime.of(12, 30), 40.0, 39, 41000, 42000);
		Game g12 = new Game(LocalDate.of(2024, 7, 28), LocalTime.of(15, 0), 149.99, 49, 42000, 43000);
		Game g13 = new Game(LocalDate.of(2024, 7, 29), LocalTime.of(10, 0), 33, 30, 43000, 44000);
		Game g14 = new Game(LocalDate.of(2024, 7, 29), LocalTime.of(9, 0), 24.0, 20, 44000, 45000);
		Game g15 = new Game(LocalDate.of(2024, 8, 1), LocalTime.of(8, 0), 100.0, 40, 45000, 46000);
		Game g16 = new Game(LocalDate.of(2024, 8, 3), LocalTime.of(22, 0), 74.0, 23, 46000, 47000);
		Game g17 = new Game(LocalDate.of(2024, 8, 3), LocalTime.of(17, 45), 62.0, 30, 47000, 48000);
		Location l1 = new Location("Piscine Georges Vallerey");
		Location l11 = new Location("Centre Aquatique de Marville");
		Location l12 = new Location("Piscine Roger Le Gall");
		Location l13 = new Location("Centre Aquatique de Neuilly-sur-Seine");
		Location l14 = new Location("Stade Aquatique Maurice Thorez");
		Location l15 = new Location("Centre Aquatique de Saint-Denis");
		Discipline d1 = new Discipline("Freestyle");
		Discipline d11 = new Discipline("Backstroke");
		Discipline d12 = new Discipline("Breaststroke");
		Discipline d13 = new Discipline("Butterfly");
		Discipline d14 = new Discipline("Individual Medley");
		Discipline d15 = new Discipline("Open Water Swimming");
		Discipline d16 = new Discipline("Relay Races");
		
		d1.setSport(s1);
		d11.setSport(s1);
		d12.setSport(s1);
		d13.setSport(s1);
		d14.setSport(s1);
		d15.setSport(s1);
		d16.setSport(s1);
		
		g1.setSport(s1);
		g11.setSport(s1);
		g12.setSport(s1);
		g13.setSport(s1);
		g14.setSport(s1);
		g15.setSport(s1);
		g16.setSport(s1);
		g17.setSport(s1);
		
		g1.addDiscipline(d1);
		g1.addDiscipline(d11);
		g11.addDiscipline(d12);
		g12.addDiscipline(d13);
		g13.addDiscipline(d13);
		g14.addDiscipline(d14);
		g14.addDiscipline(d15);
		g15.addDiscipline(d16);
		g17.addDiscipline(d1);
		g17.addDiscipline(d15);
		
		g1.setLocation(l1);
		g11.setLocation(l11);
		g12.setLocation(l12);
		g13.setLocation(l13);
		g14.setLocation(l14);
		g15.setLocation(l15);
		g16.setLocation(l15);
		g17.setLocation(l1);
		
		s1.addLocation(l1);
		s1.addLocation(l11);
		s1.addLocation(l12);
		s1.addLocation(l13);
		s1.addLocation(l14);
		s1.addLocation(l15);
		
		s1.addDiscipline(d1);
		s1.addDiscipline(d11);
		s1.addDiscipline(d12);
		s1.addDiscipline(d13);
		s1.addDiscipline(d14);
		s1.addDiscipline(d15);
		s1.addDiscipline(d16);
		
		s1.addGame(g1);
		s1.addGame(g12);
		s1.addGame(g13);
		s1.addGame(g14);
		s1.addGame(g15);
		s1.addGame(g16);
		s1.addGame(g17);
		
		lr.save(l1);
		lr.save(l11);
		lr.save(l12);
		lr.save(l13);
		lr.save(l14);
		lr.save(l15);
		
		sr.save(s1);
		
		dr.save(d1);
		dr.save(d11);
		dr.save(d12);
		dr.save(d13);
		dr.save(d14);
		dr.save(d15);
		dr.save(d16);
		
		gr.save(g1);
		gr.save(g11);
		gr.save(g12);
		gr.save(g13);
		gr.save(g14);
		gr.save(g15);
		gr.save(g16);
		gr.save(g17);
	}
	
	private void initSportSkateboarding() {
		Sport s1 = new Sport("Skateboarding");
		Game g1 = new Game(LocalDate.of(2024, 7, 26), LocalTime.of(15, 0), 20.0, 10, 50000, 51000);
		Game g11 = new Game(LocalDate.of(2024, 7, 26), LocalTime.of(12, 30), 40.0, 39, 51000, 52000);
		Game g12 = new Game(LocalDate.of(2024, 7, 28), LocalTime.of(15, 0), 149.99, 49, 52000, 53000);
		Game g13 = new Game(LocalDate.of(2024, 7, 29), LocalTime.of(10, 0), 33, 30, 53000, 54000);
		Game g14 = new Game(LocalDate.of(2024, 7, 29), LocalTime.of(9, 0), 24.0, 20, 54000, 55000);
		Game g15 = new Game(LocalDate.of(2024, 8, 1), LocalTime.of(8, 0), 100.0, 40, 55000, 56000);
		Game g16 = new Game(LocalDate.of(2024, 8, 3), LocalTime.of(22, 0), 74.0, 23, 56000, 57000);
		Game g17 = new Game(LocalDate.of(2024, 8, 3), LocalTime.of(17, 45), 62.0, 30, 57000, 58000);
		Location l1 = new Location("La Villette");
		Location l11 = new Location("Bercy Skatepark");
		Location l12 = new Location("Quai de la Seine");
		Location l13 = new Location("Trocadéro Gardens");
		Location l14 = new Location("Parc des Buttes-Chaumont");
		Location l15 = new Location("Stade Charlety");
		Discipline d1 = new Discipline("Street");
		Discipline d11 = new Discipline("Park");
		
		d1.setSport(s1);
		d11.setSport(s1);
		
		g1.setSport(s1);
		g11.setSport(s1);
		g12.setSport(s1);
		g13.setSport(s1);
		g14.setSport(s1);
		g15.setSport(s1);
		g16.setSport(s1);
		g17.setSport(s1);
		
		g1.addDiscipline(d1);
		g1.addDiscipline(d11);
		g11.addDiscipline(d1);
		g12.addDiscipline(d11);
		g13.addDiscipline(d11);
		g14.addDiscipline(d1);
		g14.addDiscipline(d11);
		g15.addDiscipline(d1);
		g17.addDiscipline(d1);
		g17.addDiscipline(d11);
		
		g1.setLocation(l1);
		g11.setLocation(l11);
		g12.setLocation(l12);
		g13.setLocation(l13);
		g14.setLocation(l14);
		g15.setLocation(l15);
		g16.setLocation(l15);
		g17.setLocation(l1);
		
		s1.addLocation(l1);
		s1.addLocation(l11);
		s1.addLocation(l12);
		s1.addLocation(l13);
		s1.addLocation(l14);
		s1.addLocation(l15);
		
		s1.addDiscipline(d1);
		s1.addDiscipline(d11);
		
		s1.addGame(g1);
		s1.addGame(g12);
		s1.addGame(g13);
		s1.addGame(g14);
		s1.addGame(g15);
		s1.addGame(g16);
		s1.addGame(g17);
		
		lr.save(l1);
		lr.save(l11);
		lr.save(l12);
		lr.save(l13);
		lr.save(l14);
		lr.save(l15);
		
		sr.save(s1);
		
		dr.save(d1);
		dr.save(d11);
		
		gr.save(g1);
		gr.save(g11);
		gr.save(g12);
		gr.save(g13);
		gr.save(g14);
		gr.save(g15);
		gr.save(g16);
		gr.save(g17);
	}
	
	private void initSportGymnastics() {
		Sport s1 = new Sport("Gymnastics");
		Game g1 = new Game(LocalDate.of(2024, 7, 26), LocalTime.of(15, 0), 20.0, 10, 60000, 61000);
		Game g11 = new Game(LocalDate.of(2024, 7, 26), LocalTime.of(12, 30), 40.0, 39, 61000, 62000);
		Game g12 = new Game(LocalDate.of(2024, 7, 28), LocalTime.of(15, 0), 149.99, 49, 62000, 63000);
		Game g13 = new Game(LocalDate.of(2024, 7, 29), LocalTime.of(10, 0), 33, 30, 63000, 64000);
		Game g14 = new Game(LocalDate.of(2024, 7, 29), LocalTime.of(9, 0), 24.0, 20, 64000, 65000);
		Game g15 = new Game(LocalDate.of(2024, 8, 1), LocalTime.of(8, 0), 100.0, 40, 65000, 66000);
		Game g16 = new Game(LocalDate.of(2024, 8, 3), LocalTime.of(22, 0), 74.0, 23, 66000, 67000);
		Game g17 = new Game(LocalDate.of(2024, 8, 3), LocalTime.of(17, 45), 62.0, 30, 67000, 68000);
		Location l1 = new Location("Palais des Sports");
		Location l11 = new Location("AccorHotels Arena");
		Location l12 = new Location("Stade Pierre de Coubertin");
		Location l13 = new Location("Paris Expo Porte de Versailles");
		Location l14 = new Location("Palais des Congrès");
		Location l15 = new Location("Le Kindarena");
		Discipline d1 = new Discipline("Artistic Gymnastics");
		Discipline d11 = new Discipline("Rhythmic Gymnastics");
		Discipline d12 = new Discipline("Trampoline Gymnastics");
		Discipline d13 = new Discipline("Acrobatic Gymnastics");
		Discipline d14 = new Discipline("Aerobic Gymnastics");
		Discipline d15 = new Discipline("Group Gymnastics");
		Discipline d16 = new Discipline("Freestyle Gymnastics");
		
		d1.setSport(s1);
		d11.setSport(s1);
		d12.setSport(s1);
		d13.setSport(s1);
		d14.setSport(s1);
		d15.setSport(s1);
		d16.setSport(s1);
		
		g1.setSport(s1);
		g11.setSport(s1);
		g12.setSport(s1);
		g13.setSport(s1);
		g14.setSport(s1);
		g15.setSport(s1);
		g16.setSport(s1);
		g17.setSport(s1);
		
		g1.addDiscipline(d1);
		g1.addDiscipline(d11);
		g11.addDiscipline(d12);
		g12.addDiscipline(d13);
		g13.addDiscipline(d13);
		g14.addDiscipline(d14);
		g14.addDiscipline(d15);
		g15.addDiscipline(d16);
		g17.addDiscipline(d1);
		g17.addDiscipline(d15);
		
		g1.setLocation(l1);
		g11.setLocation(l11);
		g12.setLocation(l12);
		g13.setLocation(l13);
		g14.setLocation(l14);
		g15.setLocation(l15);
		g16.setLocation(l15);
		g17.setLocation(l1);
		
		s1.addLocation(l1);
		s1.addLocation(l11);
		s1.addLocation(l12);
		s1.addLocation(l13);
		s1.addLocation(l14);
		s1.addLocation(l15);
		
		s1.addDiscipline(d1);
		s1.addDiscipline(d11);
		s1.addDiscipline(d12);
		s1.addDiscipline(d13);
		s1.addDiscipline(d14);
		s1.addDiscipline(d15);
		s1.addDiscipline(d16);
		
		s1.addGame(g1);
		s1.addGame(g12);
		s1.addGame(g13);
		s1.addGame(g14);
		s1.addGame(g15);
		s1.addGame(g16);
		s1.addGame(g17);
		
		lr.save(l1);
		lr.save(l11);
		lr.save(l12);
		lr.save(l13);
		lr.save(l14);
		lr.save(l15);
		
		sr.save(s1);
		
		dr.save(d1);
		dr.save(d11);
		dr.save(d12);
		dr.save(d13);
		dr.save(d14);
		dr.save(d15);
		dr.save(d16);
		
		gr.save(g1);
		gr.save(g11);
		gr.save(g12);
		gr.save(g13);
		gr.save(g14);
		gr.save(g15);
		gr.save(g16);
		gr.save(g17);
	}
}
