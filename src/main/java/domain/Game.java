package domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({"game_id", "date", "time", "price", "amount_available", "amount_left", "olympic_nr_one", "olympic_nr_two", "sport", "location"})
public class Game implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("game_id")
	private long id;

	@NotNull(message = "{validation.null}")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate date;

	@NotNull(message = "{validation.null}")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
	private LocalTime time;

	@NotNull(message = "{validation.null}")
	@DecimalMin(value = "0.01", message = "{price.validation.min}")
	@DecimalMax(value = "149.99", message = "{price.validation.max}")
	private double price;

	@NotNull(message = "{validation.null}")
	@Range(min = 1, max = 49, message = "{amountAvailable.validation.range}")
	@JsonProperty("amount_available")
	private int amountAvailable;
	
	@Range(min = 0, max = 49, message = "{amountAvailable.validation.range}")
	@JsonProperty("amount_left")
	private int amountLeft;
	
	@NotNull(message = "{validation.null}")
	@Column(unique = true)
	@JsonProperty("olympic_nr_one")
	private int olympicNrOne;
	
	@NotNull(message = "{validation.null}")
	@JsonProperty("olympic_nr_two")
	private int olympicNrTwo;
	
	@ManyToOne
//	@JsonIgnore
	private Sport sport;
	
	@NotNull(message = "{validation.null}")
	@ManyToOne
//	@JsonIgnore
	private Location location;
	
	@OneToMany(mappedBy = "game")
	@JsonIgnore
	List<Ticket> tickets = new ArrayList<>();
	
	@ManyToMany
	@JsonIgnore
	List<Discipline> disciplines = new ArrayList<>();

	public Game(LocalDate date, LocalTime time, double price,
			int amountAvailable, int olympicNrOne, int olympicNrTwo) {
		setDate(date);
		setTime(time);
		setPrice(price);
		setAmountAvailable(amountAvailable);
		setAmountLeft(amountAvailable);
		setOlympicNrOne(olympicNrOne);
		setOlympicNrTwo(olympicNrTwo);
	}

	public void addDiscipline(Discipline d) {
		disciplines.add(d);
	}
	
	public void addTicket(Ticket t) {
		tickets.add(t);
	}
}
