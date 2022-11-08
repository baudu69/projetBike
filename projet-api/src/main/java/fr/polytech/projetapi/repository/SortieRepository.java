package fr.polytech.projetapi.repository;

import fr.polytech.projetapi.model.Sortie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SortieRepository extends JpaRepository<Sortie, Integer> {
}