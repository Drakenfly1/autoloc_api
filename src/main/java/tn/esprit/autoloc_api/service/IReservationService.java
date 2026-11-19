
package tn.esprit.autoloc_api.service;

import tn.esprit.autoloc_api.web.dto.ReservationRequestDTO;
import tn.esprit.autoloc_api.web.dto.ReservationResponseDTO;
import tn.esprit.autoloc_api.web.dto.ReservationWithContratRequestDTO;

import java.util.List;

public interface IReservationService {

    ReservationResponseDTO create(ReservationRequestDTO dto);

    ReservationResponseDTO findById(Long id);

    List<ReservationResponseDTO> findAll();

    ReservationResponseDTO update(
            Long id,
            ReservationRequestDTO dto
    );

    ReservationResponseDTO affecterReservationAClient(
            Long idReservation,
            String nom,
            String prenom
    );

    ReservationResponseDTO creerReservationEtContratAssocie(
            ReservationWithContratRequestDTO requestDTO
    );

    void deleteById(Long id);
}
