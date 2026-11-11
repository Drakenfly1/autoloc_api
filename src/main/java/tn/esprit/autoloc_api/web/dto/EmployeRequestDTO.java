
package tn.esprit.autoloc_api.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import tn.esprit.autoloc_api.domain.RoleEmploye;

public record EmployeRequestDTO(

    @NotBlank(message = "Le nom est obligatoire")
    @Size(max = 50)
    String nom,

    @NotBlank(message = "Le prénom est obligatoire")
    @Size(max = 50)
    String prenom,

    @NotNull(message = "Le rôle est obligatoire")
    RoleEmploye role,

    Long idAgence

) {}
