
package tn.esprit.autoloc_api.mapper;

import org.springframework.stereotype.Component;

import tn.esprit.autoloc_api.domain.Reservation;
import tn.esprit.autoloc_api.web.dto.ReservationRequestDTO;
import tn.esprit.autoloc_api.web.dto.ReservationResponseDTO;

@Component
public class ReservationMapper {

    // Conversion Entité vers DTO
    public ReservationResponseDTO toDTO(Reservation reservation) {

        Long idClient = reservation.getClient() != null
                ? reservation.getClient().getIdClient()
                : null;

        Long idVehicule = reservation.getVehicule() != null
                ? reservation.getVehicule().getIdVehicule()
                : null;

        Long idContrat = reservation.getContrat() != null
                ? reservation.getContrat().getIdContrat()
                : null;

        return new ReservationResponseDTO(
                reservation.getIdReservation(),
                reservation.getDateDebut(),
                reservation.getDateFin(),
                reservation.getStatut(),
                idClient,
                idVehicule,
                idContrat
        );
    }

    // Conversion DTO vers Entité
    public Reservation toEntity(ReservationRequestDTO dto) {

        Reservation reservation = new Reservation();

        reservation.setDateDebut(dto.dateDebut());
        reservation.setDateFin(dto.dateFin());
        reservation.setStatut(dto.statut());

        return reservation;
    }
}
