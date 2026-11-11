
package tn.esprit.autoloc_api.web.dto;

public record AgenceResponseDTO(

    Long idAgence,
    String nom,
    String ville,
    String adresse,
    String telephone

) {}
