package domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Game implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "date")
	private LocalDate date;

	@Column(name = "time")
	private LocalTime time;

	// TODO price
	@Column(name = "price")
	private double price;

	// TODO available
	@Column(name = "amountAvailable")
	private int amountAvailable;

	// TODO # bought
	private int amount;
	
	// TODO olympic nr 1 en 2

	@ManyToOne
	private Sport sport;
	
	@ManyToOne
	private Location location;
	
	@ManyToMany
	List<Discipline> disciplines = new ArrayList<>();

	public Game(LocalDate date, LocalTime time, double price,
			int amountAvailable) {
		setDate(date);
		setTime(time);
		setPrice(price);
		setAmount(amountAvailable);
	}

	public void addDiscipline(Discipline d) {
		disciplines.add(d);
	}
}
