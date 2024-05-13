package tn.esprit.examnomprenom.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.examnomprenom.entities.enums.TypeUtilisateur;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Utilisateur implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String email;
    @Temporal(TemporalType.DATE)
    LocalDate dateInscri;
    @Enumerated(EnumType.STRING)
    TypeUtilisateur typeUtilisateur;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "utilisateur_id")
    private List<Produit> produits = new ArrayList<>();

}
