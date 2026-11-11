
package tn.esprit.autoloc_api.service;

import tn.esprit.autoloc_api.web.dto.VehiculeRequestDTO;
import tn.esprit.autoloc_api.web.dto.VehiculeResponseDTO;

import java.util.List;

public interface IVehiculeService {

    VehiculeResponseDTO create(VehiculeRequestDTO dto);

    VehiculeResponseDTO findById(Long id);

    List<VehiculeResponseDTO> findAll();

    VehiculeResponseDTO update(Long id, VehiculeRequestDTO dto);

    void deleteById(Long id);
}
