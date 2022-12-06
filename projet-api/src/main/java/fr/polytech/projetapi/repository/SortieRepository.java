package fr.polytech.projetapi.repository;

import fr.polytech.projetapi.model.Sortie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SortieRepository extends JpaRepository<Sortie, Integer> {
    List<Sortie> findByNumUtil(Integer numUtil);
}