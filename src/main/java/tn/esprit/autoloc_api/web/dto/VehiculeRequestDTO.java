
package tn.esprit.autoloc_api.web.dto;

import jakarta.validation.constraints.*;
import tn.esprit.autoloc_api.domain.CategorieVehicule;
import tn.esprit.autoloc_api.domain.StatusVehicule;

import java.math.BigDecimal;

public record VehiculeRequestDTO(

    @NotBlank(message = "L'immatriculation est obligatoire")
    @Size(max = 20)
    String immatriculation,

    @NotBlank(message = "La marque est obligatoire")
    @Size(max = 50)
    String marque,

    @NotBlank(message = "Le modèle est obligatoire")
    @Size(max = 50)
    String modele,

    @NotNull(message = "La catégorie est obligatoire")
    CategorieVehicule categorie,

    @NotNull(message = "Le tarif journalier est obligatoire")
    @DecimalMin(value = "0.0",
                message = "Le tarif ne peut pas être négatif")
    BigDecimal tarifJournalier,

    @NotNull(message = "Le statut est obligatoire")
    StatusVehicule statut,

    Long idAgence

) {}
