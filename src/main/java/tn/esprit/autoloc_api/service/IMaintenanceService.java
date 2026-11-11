
package tn.esprit.autoloc_api.service;

import tn.esprit.autoloc_api.web.dto.MaintenanceRequestDTO;
import tn.esprit.autoloc_api.web.dto.MaintenanceResponseDTO;

import java.util.List;

public interface IMaintenanceService {

    MaintenanceResponseDTO create(MaintenanceRequestDTO dto);

    MaintenanceResponseDTO findById(Long id);

    List<MaintenanceResponseDTO> findAll();

    MaintenanceResponseDTO update(Long id, MaintenanceRequestDTO dto);

    void deleteById(Long id);
}
