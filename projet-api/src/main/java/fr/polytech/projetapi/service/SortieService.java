package fr.polytech.projetapi.service;

import fr.polytech.projetapi.model.Sortie;
import fr.polytech.projetapi.repository.SortieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SortieService {
    private final SortieRepository sortieRepository;

    public SortieService(SortieRepository sortieRepository) {
        this.sortieRepository = sortieRepository;
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
}
