
package tn.esprit.autoloc_api.service;

import tn.esprit.autoloc_api.web.dto.ContratRequestDTO;
import tn.esprit.autoloc_api.web.dto.ContratResponseDTO;

import java.time.LocalDate;
import java.util.List;
import tn.esprit.autoloc_api.web.dto.ContratDTO;

public interface IContratService {

    ContratResponseDTO create(ContratRequestDTO dto);

    ContratResponseDTO findById(Long id);

    List<ContratResponseDTO> findAll();

    ContratResponseDTO update(Long id, ContratRequestDTO dto);

    ContratDTO affecterContratAReservation(
            LocalDate dateSignature,
            LocalDate dateDebut,
            LocalDate dateFin
    );


    void deleteById(Long id);
}
