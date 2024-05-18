package com.springBoot.EWDJexamenopdracht;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import domain.Game;
import domain.Sport;
import exceptions.GameNotFoundException;
import repository.GameRepository;
import repository.SportRepository;

@SpringBootTest
class SportsRestMockTest {

	@Mock
	private GameRepository mockGame;
	
	@Mock
	private SportRepository mockSport;
	
	private SportsRestController controller;
	private MockMvc mockMvc;
	private Sport sport;
	
	private static final long SPORT_ID = 1;
	private static final long GAME_ID = 1;
    private static final LocalDate DATE = LocalDate.of(2024, 8, 3);
    private static final LocalTime TIME = LocalTime.of(9, 50);
    private static final double PRICE = 50.00;
    private static final int AMOUNT_AVAILABLE = 100;
    private static final int AMOUNT_LEFT = 98;
    private static final int OLYMPIC_NR_ONE = 1;
    private static final int OLYMPIC_NR_TWO = 2; 
	
	@BeforeEach
	public void before() {
		MockitoAnnotations.openMocks(this);
		controller = new SportsRestController();
		mockMvc = standaloneSetup(controller).build();
		
		ReflectionTestUtils.setField(controller, "gr", mockGame);
		ReflectionTestUtils.setField(controller, "sr", mockSport);
		
		Sport mockSportInstance = new Sport();
		mockSportInstance.setId(SPORT_ID);
		Mockito.when(mockSport.findAll()).thenReturn(List.of(mockSportInstance));
		Mockito.when(mockSport.findById(SPORT_ID)).thenReturn(Optional.of(mockSportInstance));
		sport = mockSport.findAll().get(0);
	}
	
	// TODO fix
	@Test
	public void testGetAmountOfSeatsLeft() throws Exception {
		Game game = new Game();
		game.setAmountLeft(AMOUNT_LEFT);
		
		Mockito.when(mockGame.findById(GAME_ID)).thenReturn(Optional.of(game));
		mockMvc.perform(get("/rest/sports/{sportId}/games/{gameId}/available", SPORT_ID, GAME_ID))
		        .andExpect(status().isOk())
		        .andExpect(jsonPath("$.amount_left").value(AMOUNT_LEFT));
		
		Mockito.verify(mockGame).findById(GAME_ID);
	}
	
	@Test
    public void testGetAmountOfSeatsLeft_GameNotFound() throws Exception {
        Mockito.when(mockGame.findById(GAME_ID)).thenThrow(new GameNotFoundException(GAME_ID));
        
        Exception exception= assertThrows(Exception.class, () -> {
        	mockMvc.perform(get("/rest/sports/{sportId}/games/{gameId}/available", SPORT_ID, GAME_ID))
        	 		.andReturn();
        });
                      
        assertTrue(exception.getCause()instanceof GameNotFoundException);
        Mockito.verify(mockGame).findById(GAME_ID);
    }

	@Test
	public void testGetAllGamesBySort_emptyList() throws Exception {
		Mockito.when(mockGame.findBySportOrderByDateAscTimeAsc(sport))
				.thenReturn(new ArrayList<>());
		
		mockMvc.perform(get("/rest/sports/{sportId}/games", sport.getId()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$").isEmpty());
		
		Mockito.verify(mockGame).findBySportOrderByDateAscTimeAsc(sport);
	}
	
	@Test
	public void testGetAllGamesBySort_noEmptyList() throws Exception {
		Game g1  =aGame(DATE, TIME, PRICE, AMOUNT_AVAILABLE, OLYMPIC_NR_ONE, OLYMPIC_NR_TWO);
		Game g2  =aGame(DATE, TIME, PRICE, AMOUNT_AVAILABLE, OLYMPIC_NR_ONE + 1, OLYMPIC_NR_TWO);
		List<Game> games = List.of(g1, g2);
		
		Mockito.when(mockGame.findBySportOrderByDateAscTimeAsc(sport))
				.thenReturn(games);
		
		mockMvc.perform(get("/rest/sports/{sportId}/games", sport.getId()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$").isNotEmpty())
				.andExpect(jsonPath("$[0].date").value(DATE.toString()))
				.andExpect(jsonPath("$[0].time").value(TIME.toString()))
				.andExpect(jsonPath("$[0].price").value(PRICE))
				.andExpect(jsonPath("$[0].amount_available").value(AMOUNT_AVAILABLE))
				.andExpect(jsonPath("$[0].amount_left").value(AMOUNT_AVAILABLE))
				.andExpect(jsonPath("$[0].olympic_nr_one").value(OLYMPIC_NR_ONE))
				.andExpect(jsonPath("$[0].olympic_nr_two").value(OLYMPIC_NR_TWO))
				.andExpect(jsonPath("$[1].date").value(DATE.toString()))
				.andExpect(jsonPath("$[1].time").value(TIME.toString()))
				.andExpect(jsonPath("$[1].price").value(PRICE))
				.andExpect(jsonPath("$[1].amount_available").value(AMOUNT_AVAILABLE))
				.andExpect(jsonPath("$[1].amount_left").value(AMOUNT_AVAILABLE))
				.andExpect(jsonPath("$[1].olympic_nr_one").value(OLYMPIC_NR_ONE + 1))
				.andExpect(jsonPath("$[1].olympic_nr_two").value(OLYMPIC_NR_TWO));
		
		Mockito.verify(mockGame).findBySportOrderByDateAscTimeAsc(sport);
	}
	
	private Game aGame(LocalDate date, LocalTime time, double price, int amountAvailable, int olympicNrOne, int olympicNrTwo) {
		return new Game(date, time, price, amountAvailable, olympicNrOne, olympicNrTwo);
	}
	
}
