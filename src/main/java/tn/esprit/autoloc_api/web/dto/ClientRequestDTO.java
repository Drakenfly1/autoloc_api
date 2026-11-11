
package tn.esprit.autoloc_api.web.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record ClientRequestDTO(

    @NotBlank(message = "Le nom est obligatoire")
    @Size(max = 50)
    String nom,

    @NotBlank(message = "Le prénom est obligatoire")
    @Size(max = 50)
    String prenom,

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Format d'email invalide")
    @Size(max = 100)
    String email,

    @NotBlank(message = "Le téléphone est obligatoire")
    @Size(max = 20)
    String telephone,

    @NotNull(message = "Le numéro de permis est obligatoire")
    Integer numPermis,

    @NotNull(message = "La date d'inscription est obligatoire")
    LocalDate dateInscription

) {}
