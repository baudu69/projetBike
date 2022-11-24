package fr.polytech.projetapi.repository;

import fr.polytech.projetapi.model.Etape;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EtapeRepository extends JpaRepository<Etape, Integer> {
    List<Etape> findBySortie_Id(Integer id);

    void deleteBySortie_IdAndNumEtape(Integer idSortie, Integer numEtape);
}