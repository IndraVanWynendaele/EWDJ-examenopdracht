package domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
public class Game implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "date")
	private LocalDate date;

	@Column(name = "time")
	private LocalTime time;

	// TODO location
	private String location;

	// TODO disciplines
	private String disciplines;

	// TODO price
	@Column(name = "price")
	private double price;

	// TODO available
	@Column(name = "amountAvailable")
	private int amountAvailable;

	// TODO # bought
	private int amount;

	@Setter
	private Sport sport;

	public Game(LocalDate date, LocalTime time, String location, String disciplines, double price,
			int amountAvailable) {
		this.date = date;
		this.time = time;
		this.location = location;
		this.disciplines = disciplines;
		this.price = price;
		this.amountAvailable = amountAvailable;
	}

}
