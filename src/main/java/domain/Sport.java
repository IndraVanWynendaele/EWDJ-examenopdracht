package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@JsonPropertyOrder({"sport_id", "sport_name"})
public class Sport implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("sport_id")
    private long id;
	
	@NotBlank(message = "{validation.null}")
	@JsonProperty("sport_name")
	private String name;
	
	@OneToMany(mappedBy = "sport")
	@JsonIgnore
	private List<Game> games = new ArrayList<>();
	
	@OneToMany(mappedBy = "sport")
	@JsonIgnore
	private List<Discipline> disciplines = new ArrayList<>();
	
	@ManyToMany
	@JsonIgnore
	private List<Location> locations = new ArrayList<>();

	public Sport(String name) {
		this.name = name;
	}
	
	public void addGame(Game game) {
		games.add(game);
	}
	
	public void addDiscipline(Discipline discipline) {
		disciplines.add(discipline);
	}
	
	public void addLocation(Location location) {
		locations.add(location);
	}
}
