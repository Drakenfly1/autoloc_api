
package tn.esprit.autoloc_api.web.dto;

import jakarta.validation.constraints.NotNull;

import tn.esprit.autoloc_api.domain.StatutReservation;

import java.time.LocalDate;

public record ReservationRequestDTO(

    @NotNull(message = "La date de début est obligatoire")
    LocalDate dateDebut,

    @NotNull(message = "La date de fin est obligatoire")
    LocalDate dateFin,

    @NotNull(message = "Le statut est obligatoire")
    StatutReservation statut,

    Long idClient,

    Long idVehicule,

    Long idContrat

) {}
