package tn.esprit.examnomprenom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.examnomprenom.entities.Categorie;

public interface CategorieRepo extends JpaRepository<Categorie, Long> {
    Categorie findByNomCategorie(String nom);
}
