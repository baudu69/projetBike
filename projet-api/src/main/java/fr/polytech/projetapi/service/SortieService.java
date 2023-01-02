package fr.polytech.projetapi.service;

import fr.polytech.projetapi.model.Etape;
import fr.polytech.projetapi.model.Sortie;
import fr.polytech.projetapi.repository.SortieRepository;
import fr.polytech.projetapi.repository.UtilisateurRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class SortieService {
    private final SortieRepository sortieRepository;
    private final UtilisateurRepository utilisateurRepository;

    public SortieService(SortieRepository sortieRepository, UtilisateurRepository utilisateurRepository) {
        this.sortieRepository = sortieRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    public void deleteSortie(Integer id) {
        sortieRepository.deleteById(id);
    }

    public Optional<Sortie> getSortieByID(Integer id) {
        return sortieRepository.findById(id);
    }

    public void updateSortie(Sortie sortie) {
        sortieRepository.save(sortie);
    }

    public void addSortie(Sortie sortie, int numUtilisateur) {
        sortie.setNumUtil(numUtilisateur);
        final List<Etape> etapes = sortie.getEtapes()
                .stream()
                .sorted(Comparator.comparing(Etape::getNumEtape))
                .peek(etape -> etape.setSortie(sortie))
                .toList();
        //Calcul des vitesses moyennes des différentes étapes (sauf la 0)
        for (int i = 1; i < etapes.size(); i++) {
            etapes.get(i).setVitesseMoyenneFromPrevious((etapes.get(i).getDistanceFromPrevious() / getHoursBetweenTwoLocalTime(etapes.get(i).getHeureEtape(), etapes.get(i - 1).getHeureEtape())));
        }
        final double distanceTotale = etapes.stream().filter(etape -> etape.getDistanceFromPrevious() != null).mapToDouble(Etape::getDistanceFromPrevious).sum();
        sortie.setDistanceParcourue(distanceTotale);
        sortieRepository.save(sortie);
    }

    private static double getHoursBetweenTwoLocalTime(LocalTime time1, LocalTime time2) {
        return time2.until(time1, ChronoUnit.SECONDS) / 3600.0;
    }

    public List<Sortie> getAllSortiesByUser(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        int userId = utilisateurRepository.findByLogin(userDetails.getUsername()).orElseThrow(IllegalStateException::new).getId();
        return sortieRepository.findByNumUtil(userId);
    }
}
