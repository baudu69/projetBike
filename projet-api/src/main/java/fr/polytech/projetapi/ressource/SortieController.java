package fr.polytech.projetapi.ressource;


import fr.polytech.projetapi.model.Etape;
import fr.polytech.projetapi.model.Sortie;
import fr.polytech.projetapi.model.UserDetailsImpl;
import fr.polytech.projetapi.model.Utilisateur;
import fr.polytech.projetapi.service.SortieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/sortie")
public class SortieController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final SortieService sortieService;

    public SortieController(SortieService sortieService) {
        this.sortieService = sortieService;
    }

    @GetMapping
    public ResponseEntity<List<Sortie>> getAllSorties(Authentication authentication) {
        logger.info("REST request to get all Sorties");
        return ResponseEntity.ok(sortieService.getAllSortiesByUser(authentication));
    }

    @GetMapping("/{idSortie}")
    public ResponseEntity<Sortie> getSortieByID(@PathVariable Integer idSortie) {
        logger.info("REST request to get Sortie by ID");
        return ResponseEntity.of(sortieService.getSortieByID(idSortie));
    }

    @PostMapping
    public ResponseEntity<Sortie> createSortie(@RequestBody Sortie sortie, Authentication authentication) {
        logger.info("REST request to create Sortie");
        UserDetailsImpl utilisateur = (UserDetailsImpl) authentication.getPrincipal();
        sortieService.addSortie(sortie, utilisateur.getNumUtil());
        return ResponseEntity.ok(sortie);
    }

    @PutMapping
    public ResponseEntity<Sortie> updateSortie(@RequestBody Sortie sortie) {
        logger.info("REST request to update Sortie");
        sortieService.updateSortie(sortie);
        return ResponseEntity.ok(sortie);
    }

    @PostMapping("/{idSortie}/etape")
    public ResponseEntity<Sortie> ajouterEtape(@PathVariable Integer idSortie, @RequestBody Etape etape) {
        logger.info("REST request to add Etape to Sortie");
        sortieService.ajouterEtape(idSortie, etape);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{idSortie}")
    @Transactional
    public ResponseEntity<Void> deleteSortie(@PathVariable Integer idSortie) {
        logger.info("REST request to delete Sortie");
        sortieService.deleteSortie(idSortie);
        return ResponseEntity.noContent().build();
    }



    @DeleteMapping("/{idSortie}/etape/{numEtape}")
    @Transactional
    public ResponseEntity<Void> supprimerEtape(@PathVariable Integer idSortie, @PathVariable Integer numEtape) {
        logger.info("REST request to delete Etape from Sortie");
        sortieService.supprimerEtape(idSortie, numEtape);
        return ResponseEntity.noContent().build();
    }
}
