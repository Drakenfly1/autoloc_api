
package tn.esprit.autoloc_api.service;

import tn.esprit.autoloc_api.web.dto.EquipementRequestDTO;
import tn.esprit.autoloc_api.web.dto.EquipementResponseDTO;

import java.util.List;

public interface IEquipementService {

    EquipementResponseDTO create(EquipementRequestDTO dto);

    EquipementResponseDTO findById(Long id);

    List<EquipementResponseDTO> findAll();

    EquipementResponseDTO update(Long id, EquipementRequestDTO dto);

    void deleteById(Long id);
}
