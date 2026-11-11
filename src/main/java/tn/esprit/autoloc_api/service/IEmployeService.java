
package tn.esprit.autoloc_api.service;

import tn.esprit.autoloc_api.web.dto.EmployeRequestDTO;
import tn.esprit.autoloc_api.web.dto.EmployeResponseDTO;

import java.util.List;

public interface IEmployeService {

    EmployeResponseDTO create(EmployeRequestDTO dto);

    EmployeResponseDTO findById(Long id);

    List<EmployeResponseDTO> findAll();

    EmployeResponseDTO update(Long id, EmployeRequestDTO dto);

    void deleteById(Long id);
}
