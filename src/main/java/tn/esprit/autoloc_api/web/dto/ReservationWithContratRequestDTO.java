package tn.esprit.autoloc_api.web.dto;

import jakarta.validation.constraints.NotNull;
import tn.esprit.autoloc_api.domain.StatutReservation;
import java.time.LocalDate;

public record ReservationWithContratRequestDTO(

        @NotNull LocalDate dateDebut,
        @NotNull LocalDate dateFin,
        @NotNull LocalDate dateSignature,
        @NotNull StatutReservation statut,
        @NotNull Long idClient,
        @NotNull Long idVehicule

) {}
