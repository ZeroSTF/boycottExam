package tn.esprit.examnomprenom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.examnomprenom.entities.Produit;

public interface ProduitRepo extends JpaRepository<Produit, Long> {
    Produit findByNomProduit(String nom);
}
