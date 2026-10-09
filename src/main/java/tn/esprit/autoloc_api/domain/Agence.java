package tn.esprit.autoloc_api.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "agence")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Agence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgence;
    @Column(nullable = false, length = 50)
    private String nom;
    @Column(nullable = false, length = 50)
    private String ville;
    @Column(nullable = false, length = 100)
    private String adresse;
    @Column(nullable = false, length = 20)
    private String telephone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "agence")
    private Set<Employe> employes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "agence")
    private Set<Vehicule> vehicules;
}