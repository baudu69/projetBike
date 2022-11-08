package fr.polytech.projetapi.ressource;


import fr.polytech.projetapi.model.Sortie;
import fr.polytech.projetapi.service.SortieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<Sortie>> getAllSorties() {
        logger.info("REST request to get all Sorties");
        return ResponseEntity.ok(sortieService.getAllSorties());
    }

    @GetMapping("/{idSortie}")
    public ResponseEntity<Sortie> getSortieByID(@PathVariable Integer idSortie) {
        logger.info("REST request to get Sortie by ID");
        return ResponseEntity.of(sortieService.getSortieByID(idSortie));
    }

    @PostMapping
    public ResponseEntity<Sortie> createSortie(@RequestBody Sortie sortie) {
        logger.info("REST request to create Sortie");
        sortieService.updateSortie(sortie);
        return ResponseEntity.ok(sortie);
    }

    @PutMapping
    public ResponseEntity<Sortie> updateSortie(@RequestBody Sortie sortie) {
        logger.info("REST request to update Sortie");
        sortieService.updateSortie(sortie);
        return ResponseEntity.ok(sortie);
    }

    @DeleteMapping("/{idSortie}")
    public ResponseEntity<Void> deleteSortie(@PathVariable Integer idSortie) {
        logger.info("REST request to delete Sortie");
        sortieService.deleteSortie(idSortie);
        return ResponseEntity.ok().build();
    }
}
