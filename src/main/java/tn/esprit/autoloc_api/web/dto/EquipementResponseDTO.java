
package tn.esprit.autoloc_api.web.dto;

import java.time.LocalDate;

public record MaintenanceResponseDTO(

    Long idMaintenance,
    LocalDate dateDebut,
    LocalDate dateFin,
    String description,
    Long idVehicule

) {}
