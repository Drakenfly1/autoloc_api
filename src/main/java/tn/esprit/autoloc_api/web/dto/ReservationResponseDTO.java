
package tn.esprit.autoloc_api.web.dto;

import tn.esprit.autoloc_api.domain.StatutReservation;

import java.time.LocalDate;

public record ReservationResponseDTO(

    Long idReservation,

    LocalDate dateDebut,

    LocalDate dateFin,

    StatutReservation statut,

    Long idClient,

    Long idVehicule,

    Long idContrat

) {}
