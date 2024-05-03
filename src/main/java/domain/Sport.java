package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Sport implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "name")
	private String name;
	
	@OneToMany(mappedBy = "sport")
	private List<Game> games = new ArrayList<>();
	
	@OneToMany(mappedBy = "sport")
	private List<Discipline> disciplines = new ArrayList<>();
	
	@ManyToMany
	private List<Location> locations = new ArrayList<>();

	public Sport(String name) {
		this.name = name;
	}
	
	public void addGame(Game game) {
		games.add(game);
	}
}
