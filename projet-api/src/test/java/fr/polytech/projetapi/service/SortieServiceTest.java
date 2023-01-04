package fr.polytech.projetapi.service;

import fr.polytech.projetapi.model.Sortie;
import fr.polytech.projetapi.repository.SortieRepository;
import fr.polytech.projetapi.repository.UtilisateurRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class SortieServiceTest {

	@Autowired
	private SortieService sortieService;
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	@Autowired
	private SortieRepository sortieRepository;

	public static Sortie getSortie() {
		Sortie sortie = new Sortie();
		sortie.setDateSortie(LocalDate.now());
		sortie.setHeureDepart(LocalTime.now());
		sortie.setHeureArrivee(LocalTime.now());
		sortie.setLieuDepart("test");
		return sortie;
	}

	@Test
	void deleteSortie() {
		utilisateurRepository.save(UtilisateurServiceTest.getUser());
		Sortie sortie = getSortie();
		sortie.setNumUtil(utilisateurRepository.findByLogin("test").orElseThrow().getId());
		Sortie sortieSaved = sortieRepository.save(sortie);
		sortieService.deleteSortie(sortieSaved.getId());
		assertEquals(Optional.empty(), this.sortieRepository.findById(sortieSaved.getId()));
	}

	@Test
	void getSortieByID() {
		utilisateurRepository.save(UtilisateurServiceTest.getUser());
		Sortie sortie = getSortie();
		sortie.setNumUtil(utilisateurRepository.findByLogin("test").orElseThrow().getId());
		Sortie sortieSaved = sortieRepository.save(sortie);
		assertEquals(sortieSaved, sortieService.getSortieByID(sortieSaved.getId()).orElseThrow());
	}

	@Test
	void updateSortie() {
	}

	@Test
	void addSortie() {
	}

	@Test
	void getAllSortiesByUser() {
	}
}