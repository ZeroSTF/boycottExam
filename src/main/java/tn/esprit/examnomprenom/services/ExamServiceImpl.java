package tn.esprit.examnomprenom.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.examnomprenom.entities.Categorie;
import tn.esprit.examnomprenom.entities.Produit;
import tn.esprit.examnomprenom.entities.Utilisateur;
import tn.esprit.examnomprenom.entities.enums.Etat;
import tn.esprit.examnomprenom.entities.enums.TypeUtilisateur;
import tn.esprit.examnomprenom.repositories.CategorieRepo;
import tn.esprit.examnomprenom.repositories.ProduitRepo;
import tn.esprit.examnomprenom.repositories.UtilisateurRepo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExamServiceImpl implements IExamService {
    private final UtilisateurRepo utilisateurRepo;
    private final ProduitRepo produitRepo;
    private final CategorieRepo categorieRepo;

    @Override
    public Utilisateur ajouterUtilisateur(Utilisateur u) {
        return utilisateurRepo.save(u);
    }

    @Override
    @Transactional
    public Produit ajouterProduitEtCategories(Produit p) {
        p.getCategories().forEach(c -> {
            if (c.getId() == null) {
                c.getProduits().add(p);
            }
        });
        p.getCategories().addAll(p.getCategories());
        return produitRepo.save(p);
    }

    @Override
    @Transactional
    public void affecterProdAUser(List<String> nomProduit, String email) {
        Utilisateur u = utilisateurRepo.findByEmail(email);
        nomProduit.forEach(nom -> {
            Produit p = produitRepo.findByNomProduit(nom);
            u.getProduits().add(p);
        });
    }

    @Override
    public boolean chercherProduit(String nomProduit) {
        Produit p = produitRepo.findByNomProduit(nomProduit);
        return p.getEtat().equals(Etat.BOYCOTT);
    }

    @Override
    public List<Utilisateur> recupererUtilisateursParCriteres(String nomCategorie, LocalDate d, TypeUtilisateur tu) {
        List<Utilisateur> listU=utilisateurRepo.findByTypeUtilisateurAndDateInscriGreaterThan(tu, d);
        //return the users from listU who have bought a product from the category
        List<Utilisateur> ListFinal=new ArrayList<>();
        listU.forEach(u->{
            u.getProduits().forEach(p->{
                p.getCategories().forEach(c->{
                    if(c.getNomCategorie().equals(nomCategorie)){
                        ListFinal.add(u);
                    }
                });
            });
        });
        return ListFinal;
    }

    @Override
    @Scheduled(fixedRate = 40000)
    public void afficherEtMettreAJourProduits() {
        /*List<Produit> listP=produitRepo.findAll();
        listP.forEach(p->{
            if(p.getUtilisateur().getTypeUtilisateur().equals(TypeUtilisateur.ADMIN)){
                p.setEtat(Etat.BOYCOTT);
            }
        });*/
        List<Utilisateur> listU= utilisateurRepo.findUtilisateursByTypeUtilisateur(TypeUtilisateur.ADMIN);
        listU.forEach(u->{
            u.getProduits().forEach(p->{
                p.setEtat(Etat.BOYCOTT);
                produitRepo.save(p);
            });
        });
        List<Produit> listP=produitRepo.findAll();
        listP.forEach(p->{
            log.info(p.toString());
        });
    }

    @Override
    @Transactional
    public void desAffecterCatDeProduit(List<String> nomCategories, String nomProduit) {
        Produit p = produitRepo.findByNomProduit(nomProduit);
        nomCategories.forEach(nom -> {
            Categorie cat =categorieRepo.findByNomCategorie(nom);
            cat.getProduits().remove(p);
            p.getCategories().remove(cat);
        });
        produitRepo.save(p);
    }

}
