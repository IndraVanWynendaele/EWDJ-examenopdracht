package service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import domain.MyUser;
import domain.Role;
import lombok.NoArgsConstructor;
import repository.UserRepository;

@Service
@NoArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
//	@Autowired
//	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		MyUser user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException(email);
		}
		return new User(user.getEmail(), user.getPassword(), covertAuthorities(user.getRole()));
	}

	private Collection<? extends GrantedAuthority> covertAuthorities(Role role) {
		return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role.toString()));
	}
	
}
