package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({"discipline_id", "discipline_name"})
public class Discipline implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("discipline_id")
	private long id;
	
	@NotBlank(message = "{validation.null}")
	@JsonProperty("discipline_name")
	private String name;

	@ManyToMany(mappedBy = "disciplines")
	@JsonIgnore
	List<Game> games = new ArrayList<>();
	
	@ManyToOne
	@JsonIgnore
	private Sport sport;
	
	public Discipline(String name) {
		setName(name);
	}
}
