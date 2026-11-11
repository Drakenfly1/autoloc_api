
package tn.esprit.autoloc_api.web.dto;

import tn.esprit.autoloc_api.domain.RoleEmploye;

public record EmployeResponseDTO(

    Long idEmploye,
    String nom,
    String prenom,
    RoleEmploye role,
    Long idAgence

) {}
