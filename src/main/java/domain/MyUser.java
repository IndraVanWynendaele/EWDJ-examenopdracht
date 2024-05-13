package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class MyUser implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "{validation.null}")
	@Column(nullable = false, unique = true)
	private String email;
	
	@NotBlank(message = "{validation.null}")
	@Column(nullable = false)
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20, nullable = false)
	private Role role;
	
	@NotNull(message = "{validation.null}")
	@OneToMany(mappedBy = "user")
	List<Ticket> tickets = new ArrayList<>();
	
	public void addTicket(Ticket t) {
		tickets.add(t);
	}

	public MyUser(String email, String password, Role role) {
		setEmail(email);
		setPassword(password);
		setRole(role);
	}	
}
