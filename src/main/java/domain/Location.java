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
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@JsonPropertyOrder({"location_id", "location_name"})
public class Location implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("location_id")
	private long id;
	
	@NotBlank(message = "{validation.null}")
	@JsonProperty("location_name")
	private String name;
	
	@OneToMany(mappedBy = "location")
	@JsonIgnore
	private List<Game> games = new ArrayList<>();
	
	@ManyToMany(mappedBy = "locations")
	@JsonIgnore
	private List<Sport> sports = new ArrayList<>();

	public Location(String name) {
		this.name = name;
	}		
	
	public void addSport(Sport sport) {
		sports.add(sport);
	}
	
	public void addGame(Game game) {
		games.add(game);
	}
	
}
