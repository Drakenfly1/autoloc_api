
package tn.esprit.autoloc_api.web.dto;

import tn.esprit.autoloc_api.domain.CategorieVehicule;
import tn.esprit.autoloc_api.domain.StatusVehicule;

import java.math.BigDecimal;

public record VehiculeResponseDTO(

    Long idVehicule,
    String immatriculation,
    String marque,
    String modele,
    CategorieVehicule categorie,
    BigDecimal tarifJournalier,
    StatusVehicule statut,
    Long idAgence

) {}
