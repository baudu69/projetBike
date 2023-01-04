package fr.polytech.projetapi.service;

import fr.polytech.projetapi.model.Utilisateur;
import fr.polytech.projetapi.repository.UtilisateurRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class UtilisateurServiceTest {

	@Autowired
	private UtilisateurService utilisateurService;
	@Autowired
	private UtilisateurRepository utilisateurRepository;

	public static Utilisateur getUser() {
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setNomUtil("test");
		utilisateur.setPrenomUtil("test");
		utilisateur.setDateNaissance(LocalDate.now());
		utilisateur.setTaille(1.80);
		utilisateur.setPoids(80);
		utilisateur.setLogin("test");
		utilisateur.setPassword("test");
		return utilisateur;
	}

	@Test
	void findByLogin() {
		this.utilisateurRepository.save(getUser());
		assertEquals(utilisateurService.findByLogin("test").orElseThrow(), getUser());
	}

}