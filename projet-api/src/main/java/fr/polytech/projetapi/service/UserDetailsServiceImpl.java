package fr.polytech.projetapi.service;

import fr.polytech.projetapi.dto.SignUpRequest;
import fr.polytech.projetapi.model.UserDetailsImpl;
import fr.polytech.projetapi.model.Utilisateur;
import fr.polytech.projetapi.repository.UtilisateurRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private final UtilisateurRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public UserDetailsServiceImpl(UtilisateurRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return new UserDetailsImpl(userRepository.findByLogin(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username)));
	}

	public void signUp(SignUpRequest signUpRequest) {
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setLogin(signUpRequest.username());
		utilisateur.setNomUtil(signUpRequest.lastname());
		utilisateur.setPrenomUtil(signUpRequest.firstname());
		utilisateur.setPassword(this.passwordEncoder.encode(signUpRequest.password()));
		utilisateur.setPoids(signUpRequest.poids());
		utilisateur.setTaille(signUpRequest.taille());

		userRepository.save(utilisateur);
	}
}
