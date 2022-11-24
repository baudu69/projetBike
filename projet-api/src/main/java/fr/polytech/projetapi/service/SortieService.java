package fr.polytech.projetapi.service;

import fr.polytech.projetapi.model.Etape;
import fr.polytech.projetapi.model.Sortie;
import fr.polytech.projetapi.repository.EtapeRepository;
import fr.polytech.projetapi.repository.SortieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SortieService {
    private final SortieRepository sortieRepository;
    private final EtapeRepository etapeRepository;

    public SortieService(SortieRepository sortieRepository, EtapeRepository etapeRepository) {
        this.sortieRepository = sortieRepository;
        this.etapeRepository = etapeRepository;
    }

    public void deleteSortie(Integer id) {
        sortieRepository.deleteById(id);
    }

    public Optional<Sortie> getSortieByID(Integer id) {
        return sortieRepository.findById(id);
    }

    public List<Sortie> getAllSorties() {
        return sortieRepository.findAll();
    }

    public void updateSortie(Sortie sortie) {
        sortieRepository.save(sortie);
    }

    public void addSortie(Sortie sortie) {
        sortieRepository.save(sortie);
    }

    public void ajouterEtape(Integer idSortie, Etape etape) {
        etape.setNumSortie(idSortie);
        int lastNumEtape = etapeRepository.findBySortie_Id(idSortie)
                .stream()
                .mapToInt(Etape::getNumEtape)
                .max()
                .orElse(0);
        etape.setNumEtape(lastNumEtape + 1);
        etapeRepository.save(etape);
    }

    public void supprimerEtape(Integer idSortie, Integer numEtape) {
        etapeRepository.deleteBySortie_IdAndNumEtape(idSortie, numEtape);
    }
}
