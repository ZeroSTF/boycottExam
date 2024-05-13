package tn.esprit.examnomprenom.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examnomprenom.entities.Produit;
import tn.esprit.examnomprenom.entities.Utilisateur;
import tn.esprit.examnomprenom.entities.enums.TypeUtilisateur;
import tn.esprit.examnomprenom.services.IExamService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exam")
public class ExamController {
    private final IExamService examService;

    @PostMapping("/ajouterUtilisateur")
    public Utilisateur ajouterUtilisateur(@RequestBody Utilisateur u) {
        return examService.ajouterUtilisateur(u);
    }

    @PostMapping("/ajouterProduitEtCategories")
    public Produit ajouterProduitEtCategories(@RequestBody Produit p) {
        return examService.ajouterProduitEtCategories(p);
    }

    @PostMapping("/affecterProdAUser/{email}")
    public void affecterProdAUser(@RequestBody List<String> nomProduit, @PathVariable String email) {
        examService.affecterProdAUser(nomProduit, email);
    }

    @GetMapping("/chercherProduit/{nomProduit}")
    public boolean chercherProduit(@PathVariable String nomProduit) {
        return examService.chercherProduit(nomProduit);
    }

    @GetMapping("/recupererUtilisateursParCriteres/{nomCategorie}/{d}/{tu}")
    public List<Utilisateur> recupererUtilisateursParCriteres(@PathVariable String nomCategorie, @PathVariable String d, @PathVariable String tu) {
        return examService.recupererUtilisateursParCriteres(nomCategorie, LocalDate.parse(d), TypeUtilisateur.valueOf(tu));
    }

    @PostMapping("/desAffecterCatDeProduit/{nomProduit}")
    public void desAffecterCatDeProduit(@RequestBody List<String> nomCategories, @PathVariable String nomProduit) {
        examService.desAffecterCatDeProduit(nomCategories, nomProduit);
    }
}
