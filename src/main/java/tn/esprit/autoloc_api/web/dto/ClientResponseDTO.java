
package tn.esprit.autoloc_api.web.dto;

import java.time.LocalDate;

public record ClientResponseDTO(

    Long idClient,
    String nom,
    String prenom,
    String email,
    String telephone,
    Integer numPermis,
    LocalDate dateInscription

) {}
