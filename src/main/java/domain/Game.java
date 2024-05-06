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

	@NotNull(message = "{validation.null}")
	@Column(name = "date")
	private LocalDate date;

	@NotNull(message = "{validation.null}")
	@Column(name = "time")
	private LocalTime time;

	@NotNull(message = "{validation.null}")
	@Range(min = 1, max = 149, message = "{price.validation.range}")
	@Column(name = "price")
	private double price;

	@NotNull(message = "{validation.null}")
	@Range(min = 1, max = 49, message = "{amountAvailable.validation.range}")
	@Column(name = "amountAvailable")
	private int amountAvailable;
	
	@NotNull(message = "{validation.null}")
	@Column(name = "olympicNrOne", unique = true)
	private int olympicNrOne;
	
	@NotNull(message = "{validation.null}")
	@Column(name = "olympicNrTwo")
	private int olympicNrTwo;
	
	@ManyToOne
	private Sport sport;
	
	@NotNull(message = "{validation.null}")
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
