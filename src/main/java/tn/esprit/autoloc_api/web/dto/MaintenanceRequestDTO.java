
package tn.esprit.autoloc_api.web.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record MaintenanceRequestDTO(

    @NotNull(message = "La date de début est obligatoire")
    LocalDate dateDebut,

    LocalDate dateFin,

    @Size(
        max = 500,
        message = "La description ne doit pas dépasser 500 caractères"
    )
    String description,

    Long idVehicule

) {}
