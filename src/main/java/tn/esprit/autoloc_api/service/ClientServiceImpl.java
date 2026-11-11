
package tn.esprit.autoloc_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import tn.esprit.autoloc_api.domain.Client;
import tn.esprit.autoloc_api.exception.ResourceNotFoundException;
import tn.esprit.autoloc_api.mapper.ClientMapper;
import tn.esprit.autoloc_api.repository.IClientRepository;
import tn.esprit.autoloc_api.web.dto.ClientRequestDTO;
import tn.esprit.autoloc_api.web.dto.ClientResponseDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements IClientService {

    private final IClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public ClientResponseDTO create(ClientRequestDTO dto) {

        Client client = clientMapper.toEntity(dto);

        Client saved = clientRepository.save(client);

        return clientMapper.toDTO(saved);
    }

    @Override
    public ClientResponseDTO findById(Long id) {

        Client client = clientRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Client", id)
                );

        return clientMapper.toDTO(client);
    }

    @Override
    public List<ClientResponseDTO> findAll() {

        return clientRepository.findAll()
                .stream()
                .map(clientMapper::toDTO)
                .toList();
    }

    @Override
    public ClientResponseDTO update(
            Long id, ClientRequestDTO dto) {

        Client existant = clientRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Client", id)
                );

        existant.setNom(dto.nom());
        existant.setPrenom(dto.prenom());
        existant.setEmail(dto.email());
        existant.setTelephone(dto.telephone());
        existant.setNumPermis(dto.numPermis());
        existant.setDateInscription(dto.dateInscription());

        Client updated = clientRepository.save(existant);

        return clientMapper.toDTO(updated);
    }

    @Override
    public void deleteById(Long id) {

        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Client", id);
        }

        clientRepository.deleteById(id);
    }
}
