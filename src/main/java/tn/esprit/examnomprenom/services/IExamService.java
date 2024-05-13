package tn.esprit.examnomprenom.services;

import tn.esprit.examnomprenom.entities.Produit;
import tn.esprit.examnomprenom.entities.Utilisateur;
import tn.esprit.examnomprenom.entities.enums.TypeUtilisateur;

import java.time.LocalDate;
import java.util.List;

public interface IExamService {
    Utilisateur ajouterUtilisateur(Utilisateur u);
    Produit ajouterProduitEtCategories(Produit p);
    void affecterProdAUser(List<String> nomProduit, String email);
    boolean chercherProduit(String nomProduit);
    List<Utilisateur> recupererUtilisateursParCriteres(String nomCategorie, LocalDate d, TypeUtilisateur tu);
    void afficherEtMettreAJourProduits();
    void desAffecterCatDeProduit(List<String> nomCategories, String nomProduit);
}
