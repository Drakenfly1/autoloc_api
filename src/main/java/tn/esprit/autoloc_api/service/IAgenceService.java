
package tn.esprit.autoloc_api.service;

import tn.esprit.autoloc_api.web.dto.AgenceRequestDTO;
import tn.esprit.autoloc_api.web.dto.AgenceResponseDTO;

import java.util.List;

public interface IAgenceService {

    AgenceResponseDTO create(AgenceRequestDTO dto);

    AgenceResponseDTO findById(Long id);

    List<AgenceResponseDTO> findAll();

    AgenceResponseDTO update(Long id, AgenceRequestDTO dto);

    void deleteById(Long id);
}
