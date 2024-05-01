package domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Game implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "gameDate")
	private LocalDate gameDate;
	
	@Column(name = "gameTime")
	private LocalTime gameTime;
	
	@OneToOne
	private Location location;
	
	@OneToMany
	List<Discipline> disciplines;
	
	@Column(name = "price")
	private double price;
	
	@Column(name = "amountAvailable")
	private long amountAvailable;
	
	@Column(name = "olympicNrOne")
	private long olympicNrOne;
	
	@Column(name = "olympicNrTwo")
	private long olympicNrTwo;

	public Game(long id, LocalDate gameDate, LocalTime gameTime, Location location, List<Discipline> disciplines, double price, long amountAvailable, long olympicNrOne, long olympicNrTwo) {


		this.id = id;
		this.gameDate = gameDate;
		this.gameTime = gameTime;
		this.location = location;
		this.disciplines = disciplines;
		this.price = price;
		this.amountAvailable = amountAvailable;
		this.olympicNrOne = olympicNrOne;
		this.olympicNrTwo = olympicNrTwo;
	}
	
}
