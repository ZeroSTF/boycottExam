package tn.esprit.examnomprenom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.examnomprenom.entities.Utilisateur;
import tn.esprit.examnomprenom.entities.enums.TypeUtilisateur;

import java.time.LocalDate;
import java.util.List;

public interface UtilisateurRepo extends JpaRepository<Utilisateur, Long>{
    Utilisateur findByEmail(String email);
    List<Utilisateur> findByTypeUtilisateurAndDateInscriGreaterThan(TypeUtilisateur typeUtilisateur, LocalDate date);
    List<Utilisateur> findUtilisateursByTypeUtilisateur(TypeUtilisateur typeUtilisateur);

}
