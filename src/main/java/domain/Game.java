package domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Range;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Game implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	// TODO custom validator: liggen  tussen  26  juli 2024 en 11 augustus 2024.
	@Column(name = "date")
	private LocalDate date;

	@NotNull
	// TODO custom validator: Aanvanguur vanaf 8 uur.
	@Column(name = "time")
	private LocalTime time;

	@NotNull
	@Range(min = 1, max = 149, message = "{price.validation.range}")
	@Column(name = "price")
	private double price;

	@NotNull
	@Range(min = 1, max = 49, message = "{amountAvailable.validation.range}")
	@Column(name = "amountAvailable")
	private int amountAvailable;
	
	@Column(name = "olympicNrOne", unique = true)
	private int olympicNrOne;
	
	@Column(name = "olympicNrTwo")
	private int olympicNrTwo;
	
	@ManyToOne
	private Sport sport;
	
	@ManyToOne
	private Location location;
	
	// TODO custom validator: Disciplines  mag  leeg  zijn.  Maximum 2mogen  ingevuld  worden.Twee  dezelfde disciplines mogen niet
	@ManyToMany
	List<Discipline> disciplines = new ArrayList<>();

	public Game(LocalDate date, LocalTime time, double price,
			int amountAvailable, int olympicNrOne, int olympicNrTwo) {
		setDate(date);
		setTime(time);
		setPrice(price);
		setAmountAvailable(amountAvailable);
		setOlympicNrOne(olympicNrOne);
		setOlympicNrTwo(olympicNrTwo);
	}

	public void addDiscipline(Discipline d) {
		disciplines.add(d);
	}
}
