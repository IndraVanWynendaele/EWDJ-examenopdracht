package com.springBoot.EWDJexamenopdracht;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

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
import repository.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
class SportsControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private GameRepository gr;
	@Autowired
	private LocationRepository lr;
	@Autowired
	private SportRepository sr;
	@Autowired
	private DisciplineRepository dr;
	@Autowired
	private UserRepository ur;
	
	private static final LocalDate DATE = LocalDate.of(2024, 8, 2);
	private static final LocalTime TIME = LocalTime.of(13, 13);
	private static final long SPORT_ID = 1;
	private static final long GAME_ID = 1;
	private static final double PRICE = 18.2;
	private static final int AMOUNT_AVAILABLE = 44;
	private static final int OLYMPIC_NR_ONE = 22334;
	private static final int OLYMPIC_NR_TWO = 22336;
	private Sport sport;
	private Location location;
	private Game game;
	private Discipline d1;
	private Discipline d2;
	private Discipline d3;
	
	public void init() {
		sport = sr.findById(SPORT_ID).get();
		location = lr.findBySports(sport).getFirst();
		d1 = sport.getDisciplines().get(0);
	    d2 = sport.getDisciplines().get(1);
	    d3 = sport.getDisciplines().get(2);
	    game = sport.getGames().getFirst();
	}

	// GET sports
	@WithMockUser(username = "user@javaweb.com", roles = {"USER"})
	@Test
	public void testAccessSportsWithCorrectRoleUser() throws Exception {
		mockMvc.perform(get("/sports"))
				.andExpect(status().isOk())
				.andExpect(view().name("sportsTable"))
				.andExpect(model().attributeExists("sportsList"));
	}
	
	@WithMockUser(username = "admin@javaweb.com", roles = {"ADMIN"})
	@Test
	public void testAccessSportsWithCorrectRoleAdmin() throws Exception {
		mockMvc.perform(get("/sports"))
				.andExpect(status().isOk())
				.andExpect(view().name("sportsTable"))
				.andExpect(model().attributeExists("sportsList"));
	}
	
	@WithMockUser(username = "user@javaweb.com", roles = {"NOT_USER"})
	@Test
	public void testAccessGWithIncorrectRole() throws Exception {
		mockMvc.perform(get("/sports"))
				.andExpect(status().isForbidden());
	}
	
	// GET games
	@WithMockUser(username = "user@javaweb.com", roles = {"USER"})
	@Test
	public void testAccessGamesWithCorrectRoleUser() throws Exception {
		mockMvc.perform(get("/sports/" + SPORT_ID + "/games"))
				.andExpect(status().isOk())
				.andExpect(view().name("gamesTable"))
				.andExpect(model().attributeExists("sport"))
				.andExpect(model().attributeExists("games"));
	}
	
	@WithMockUser(username = "admin@javaweb.com", roles = {"ADMIN"})
	@Test
	public void testAccessGamesWithCorrectRoleAdmin() throws Exception {
		mockMvc.perform(get("/sports/"  + SPORT_ID + "/games"))
				.andExpect(status().isOk())
				.andExpect(view().name("gamesTable"))
				.andExpect(model().attributeExists("sport"))
				.andExpect(model().attributeExists("games"));
	}
	
	@WithMockUser(username = "user@javaweb.com", roles = {"NOT_USER"})
	@Test
	public void testAccessGamesWithIncorrectRole() throws Exception {
		mockMvc.perform(get("/sports/"  + SPORT_ID + "/games"))
				.andExpect(status().isForbidden());
	}
	
	// GET BUY tickets
	@WithMockUser(username = "user@javaweb.com", roles = {"USER"})
	@Test
	public void testAccessBuyTicketsWithCorrectRoleUser() throws Exception {
		mockMvc.perform(get("/sports/" + SPORT_ID + "/games/" + GAME_ID + "/buy"))
				.andExpect(status().isOk())
				.andExpect(view().name("buyTickets"))
				.andExpect(model().attributeExists("amountOfTickets"))
				.andExpect(model().attributeExists("sport"))
				.andExpect(model().attributeExists("ticket"))
				.andExpect(model().attributeExists("game"));
	}
	
	@WithMockUser(username = "admin@javaweb.com", roles = {"ADMIN"})
	@Test
	public void testAccessBuyTicketsWithIncorrectRoleAdmin() throws Exception {
		mockMvc.perform(get("/sports/" + SPORT_ID + "/games/" + GAME_ID + "/buy"))
				.andExpect(status().isForbidden());
	}
	
	@WithMockUser(username = "user@javaweb.com", roles = {"NOT_USER"})
	@Test
	public void testAccessBuyTicketsWithIncorrectRole() throws Exception {
		mockMvc.perform(get("/sports/" + SPORT_ID + "/games/" + GAME_ID + "/buy"))
				.andExpect(status().isForbidden());
	}
	
	// BUY tickets
	@WithMockUser(username = "user@javaweb.com", roles = {"USER"})
	@Test
	public void testBuyTicketsSuccess() throws Exception {
		init();
		MvcResult res = mockMvc.perform(post("/sports/" + SPORT_ID + "/games/" + GAME_ID + "/buy")
				.with(csrf())
				.param("amount", String.valueOf(game.getAmountLeft())))
				.andReturn();
		assertEquals("/sports/" + SPORT_ID + "/games", res.getResponse().getRedirectedUrl());
	}
	
	@WithMockUser(username = "user@javaweb.com", roles = {"USER"})
	@Test
	public void testBuyTicketsNotEnoughLeft() throws Exception {
		init();
		MvcResult res = mockMvc.perform(post("/sports/" + SPORT_ID + "/games/" + GAME_ID + "/buy")
				.with(csrf())
				.param("amount", String.valueOf(game.getAmountLeft() + 1)))
				.andReturn();
		assertTrue(res.getResponse().getContentAsString().contains("There are not enough tickets available"));
	}
	
	// TODO fix
	@WithMockUser(username = "user@javaweb.com", roles = {"USER"})
	@Test
	public void testBuyTicketsMoreThanTwenty() throws Exception {
		init();
		MvcResult res = mockMvc.perform(post("/sports/" + SPORT_ID + "/games/" + GAME_ID + "/buy")
				.with(csrf())
				.param("amount", String.valueOf(21)))
				.andReturn();
		assertTrue(res.getResponse().getContentAsString().contains("You cannot purchase more than 20 tickets at once"));
	}
	
	// TODO fix
	@WithMockUser(username = "user@javaweb.com", roles = {"USER"})
	@Test
	public void testBuyTicketsMoreThanHundredInAccount() throws Exception {
		init();
		MyUser user = ur.findByEmail("user@javaweb.com");
		for (int i = 0; i < 100 - user.getTickets().size(); i++) {
			 user.addTicket(new Ticket());
		}
		MvcResult res = mockMvc.perform(post("/sports/" + SPORT_ID + "/games/" + GAME_ID + "/buy")
				.with(csrf())
				.param("amount", String.valueOf(1)))
				.andReturn();
		assertTrue(res.getResponse().getContentAsString().contains("You can only have 100 tickets in your account"));
	}
	
	// GET ADD game
	@WithMockUser(username = "user@javaweb.com", roles = {"USER"})
	@Test
	public void testAccessAddGameWithIncorrectRoleUser() throws Exception {
		mockMvc.perform(get("/sports"  + SPORT_ID + "/games/add"))
		.andExpect(status().isForbidden());
	}
	
	@WithMockUser(username = "admin@javaweb.com", roles = {"ADMIN"})
	@Test
	public void testAccessAddGamesWithCorrectRoleAdmin() throws Exception {
		mockMvc.perform(get("/sports/" + SPORT_ID + "/games/add"))
		.andExpect(status().isOk())
		.andExpect(view().name("newGame"))
		.andExpect(model().attributeExists("disciplines"))
		.andExpect(model().attributeExists("sport"))
		.andExpect(model().attributeExists("locations"))
		.andExpect(model().attributeExists("game"));
	}
	
	@WithMockUser(username = "user@javaweb.com", roles = {"NOT_USER"})
	@Test
	public void testAccessAddGameWithIncorrectRole() throws Exception {
		mockMvc.perform(get("/sports"  + SPORT_ID + "/games/add"))
				.andExpect(status().isForbidden());
	}
	
	// ADD game
	@WithMockUser(username = "admin@javaweb.com", roles = {"ADMIN"})
	@Test
	public void testAddGameValidParametersNoDisciplines() throws Exception {	
		init();
		MvcResult res = mockMvc.perform(post("/sports/{id}/games/add", SPORT_ID)
				.with(csrf())
				.param("date", DATE.toString())
				.param("time", TIME.toString())
				.param("location", String.valueOf(location.getId()))
				.param("price", String.valueOf(PRICE))
				.param("amountAvailable", String.valueOf(AMOUNT_AVAILABLE))
				.param("olympicNrOne", String.valueOf(OLYMPIC_NR_ONE))
				.param("olympicNrTwo", String.valueOf(OLYMPIC_NR_TWO)))
				.andReturn();
		
		assertEquals("/sports/" + SPORT_ID + "/games", res.getResponse().getRedirectedUrl());
	}
	
	@WithMockUser(username = "admin@javaweb.com", roles = {"ADMIN"})
	@Test
	public void testAddGameValidParametersOneDiscipline() throws Exception {	
		init();
		MvcResult res = mockMvc.perform(post("/sports/{id}/games/add", SPORT_ID)
				.with(csrf())
				.param("date", DATE.toString())
				.param("time", TIME.toString())
				.param("location", String.valueOf(location.getId()))
				.param("price", String.valueOf(PRICE))
				.param("amountAvailable", String.valueOf(AMOUNT_AVAILABLE))
				.param("olympicNrOne", String.valueOf(OLYMPIC_NR_ONE + 1))
				.param("olympicNrTwo", String.valueOf(OLYMPIC_NR_TWO))
				.param("disciplines", String.valueOf(d1.getId())))
				.andReturn();
		
		assertEquals("/sports/" + SPORT_ID + "/games", res.getResponse().getRedirectedUrl());
	}
	
	@WithMockUser(username = "admin@javaweb.com", roles = {"ADMIN"})
	@Test
	public void testAddGameValidParametersTwoDisciplines() throws Exception {	
		init();
		MvcResult res = mockMvc.perform(post("/sports/{id}/games/add", SPORT_ID)
				.with(csrf())
				.param("date", DATE.toString())
				.param("time", TIME.toString())
				.param("location", String.valueOf(location.getId()))
				.param("price", String.valueOf(PRICE))
				.param("amountAvailable", String.valueOf(AMOUNT_AVAILABLE))
				.param("olympicNrOne", String.valueOf(OLYMPIC_NR_ONE + 2))
				.param("olympicNrTwo", String.valueOf(OLYMPIC_NR_TWO))
				.param("disciplines", String.valueOf(d1.getId()))
				.param("disciplines", String.valueOf(d2.getId())))
				.andReturn();
		
		assertEquals("/sports/" + SPORT_ID + "/games", res.getResponse().getRedirectedUrl());
	}
	
	@WithMockUser(username = "admin@javaweb.com", roles = {"ADMIN"})
	@Test
	public void testAddGameInvalidOlympicNrOneLength() throws Exception {	    
		init();
	    MvcResult res = mockMvc.perform(post("/sports/{id}/games/add", SPORT_ID)
	            .with(csrf())
	            .param("date", DATE.toString())
	            .param("time", TIME.toString())
	            .param("location", String.valueOf(location.getId()))
	            .param("price", String.valueOf(PRICE))
	            .param("amountAvailable", String.valueOf(AMOUNT_AVAILABLE))
	            .param("olympicNrOne", "1234")
	            .param("olympicNrTwo", String.valueOf(OLYMPIC_NR_TWO)))
	            .andReturn();
	    
	    assertTrue(res.getResponse().getContentAsString().contains("Should have 5 digits"));
	}
	
	@WithMockUser(username = "admin@javaweb.com", roles = {"ADMIN"})
	@Test
	public void testAddGameOlympicNrOneFirstAndLastSame() throws Exception {
		init();
	    MvcResult res = mockMvc.perform(post("/sports/{id}/games/add", SPORT_ID)
	            .with(csrf())
	            .param("date", DATE.toString())
	            .param("time", TIME.toString())
	            .param("location", String.valueOf(location.getId()))
	            .param("price", String.valueOf(PRICE))
	            .param("amountAvailable", String.valueOf(AMOUNT_AVAILABLE))
	            .param("olympicNrOne", "12341")
	            .param("olympicNrTwo", String.valueOf(OLYMPIC_NR_TWO)))
	            .andReturn();
	    
	    assertTrue(res.getResponse().getContentAsString().contains("The first and last digit must be different"));
	}

	@WithMockUser(username = "admin@javaweb.com", roles = {"ADMIN"})
	@Test
	public void testAddGameOlympicNrTwoOutOfRange() throws Exception {
		init();
		MvcResult res = mockMvc.perform(post("/sports/{id}/games/add", SPORT_ID)
	            .with(csrf())
	            .param("date", DATE.toString())
	            .param("time", TIME.toString())
	            .param("location", String.valueOf(location.getId()))
	            .param("price", String.valueOf(PRICE))
	            .param("amountAvailable", String.valueOf(AMOUNT_AVAILABLE))
	            .param("olympicNrOne", String.valueOf(OLYMPIC_NR_ONE + 3))
	            .param("olympicNrTwo", String.valueOf(OLYMPIC_NR_ONE + 2000)))
	            .andReturn();
	    
	    assertTrue(res.getResponse().getContentAsString().contains("Should be in range [olympic number one - 1000, olympic number one + 1000]"));
	}

	@WithMockUser(username = "admin@javaweb.com", roles = {"ADMIN"})
	@Test
	public void testAddGameDateOutOfRange() throws Exception {
		init();
	    MvcResult res = mockMvc.perform(post("/sports/{id}/games/add", SPORT_ID)
	            .with(csrf())
	            .param("date", LocalDate.now().toString())
	            .param("time", TIME.toString())
	            .param("location", String.valueOf(location.getId()))
	            .param("price", String.valueOf(PRICE))
	            .param("amountAvailable", String.valueOf(AMOUNT_AVAILABLE))
	            .param("olympicNrOne", String.valueOf(OLYMPIC_NR_ONE + 4))
	            .param("olympicNrTwo", String.valueOf(OLYMPIC_NR_TWO)))
	            .andReturn();
	    
	    assertTrue(res.getResponse().getContentAsString().contains("Should be between 26/07/2024 and 11/08/2024"));
	}
	
	@WithMockUser(username = "admin@javaweb.com", roles = {"ADMIN"})
	@Test
	public void testAddGameTimeBeforeEightAM() throws Exception {
		init();
	    MvcResult res = mockMvc.perform(post("/sports/{id}/games/add", SPORT_ID)
	            .with(csrf())
	            .param("date", DATE.toString())
	            .param("time", "07:59")
	            .param("location", String.valueOf(location.getId()))
	            .param("price", String.valueOf(PRICE))
	            .param("amountAvailable", String.valueOf(AMOUNT_AVAILABLE))
	            .param("olympicNrOne", String.valueOf(OLYMPIC_NR_ONE + 5))
	            .param("olympicNrTwo", String.valueOf(OLYMPIC_NR_TWO)))
	            .andReturn();
	    
	    assertTrue(res.getResponse().getContentAsString().contains("The game can only start at 08:00"));
	}
	
	@WithMockUser(username = "admin@javaweb.com", roles = {"ADMIN"})
	@Test
	public void testAddGameMoreThanTwoDisciplines() throws Exception {   
		init();
	    MvcResult res = mockMvc.perform(post("/sports/{id}/games/add", SPORT_ID)
	            .with(csrf())
	            .param("date", DATE.toString())
	            .param("time", "07:59")
	            .param("location", String.valueOf(location.getId()))
	            .param("price", String.valueOf(PRICE))
	            .param("amountAvailable", String.valueOf(AMOUNT_AVAILABLE))
	            .param("olympicNrOne", String.valueOf(OLYMPIC_NR_ONE + 6))
	            .param("olympicNrTwo", String.valueOf(OLYMPIC_NR_TWO))
	            .param("disciplines", String.valueOf(d1.getId()))
	            .param("disciplines", String.valueOf(d2.getId()))
	            .param("disciplines", String.valueOf(d3.getId())))
	            .andReturn();
	    
	    assertTrue(res.getResponse().getContentAsString().contains("Amount of disciplines should be between 0 and 2"));
	}
	
	@WithMockUser(username = "admin@javaweb.com", roles = {"ADMIN"})
	@Test
	public void testAddGameMoreThanSameDisciplines() throws Exception {
		init();
		MvcResult res = mockMvc.perform(post("/sports/{id}/games/add", SPORT_ID)
	            .with(csrf())
	            .param("date", DATE.toString())
	            .param("time", "07:59")
	            .param("location", String.valueOf(location.getId()))
	            .param("price", String.valueOf(PRICE))
	            .param("amountAvailable", String.valueOf(AMOUNT_AVAILABLE))
	            .param("olympicNrOne", String.valueOf(OLYMPIC_NR_ONE + 7))
	            .param("olympicNrTwo", String.valueOf(OLYMPIC_NR_TWO))
	            .param("disciplines", String.valueOf(d1.getId()))
	            .param("disciplines", String.valueOf(d1.getId())))
	            .andReturn();
	    
	    assertTrue(res.getResponse().getContentAsString().contains("You cannot have the same discipline more than once"));
	}
}
