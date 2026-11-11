
package tn.esprit.autoloc_api.service;

import tn.esprit.autoloc_api.web.dto.ClientRequestDTO;
import tn.esprit.autoloc_api.web.dto.ClientResponseDTO;

import java.util.List;

public interface IClientService {

    ClientResponseDTO create(ClientRequestDTO dto);

    ClientResponseDTO findById(Long id);

    List<ClientResponseDTO> findAll();

    ClientResponseDTO update(Long id, ClientRequestDTO dto);

    void deleteById(Long id);
}
