
package tn.esprit.autoloc_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import tn.esprit.autoloc_api.domain.Agence;
import tn.esprit.autoloc_api.exception.ResourceNotFoundException;
import tn.esprit.autoloc_api.mapper.AgenceMapper;
import tn.esprit.autoloc_api.repository.IAgenceRepository;
import tn.esprit.autoloc_api.web.dto.AgenceRequestDTO;
import tn.esprit.autoloc_api.web.dto.AgenceResponseDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgenceServiceImpl implements IAgenceService {

    private final IAgenceRepository agenceRepository;
    private final AgenceMapper agenceMapper;

    @Override
    public AgenceResponseDTO create(AgenceRequestDTO dto) {

        Agence agence = agenceMapper.toEntity(dto);

        Agence saved = agenceRepository.save(agence);

        return agenceMapper.toDTO(saved);
    }

    @Override
    public AgenceResponseDTO findById(Long id) {

        Agence agence = agenceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Agence", id)
                );

        return agenceMapper.toDTO(agence);
    }

    @Override
    public List<AgenceResponseDTO> findAll() {

        return agenceRepository.findAll()
                .stream()
                .map(agenceMapper::toDTO)
                .toList();
    }

    @Override
    public AgenceResponseDTO update(
            Long id, AgenceRequestDTO dto) {

        Agence existante = agenceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Agence", id)
                );

        existante.setNom(dto.nom());
        existante.setVille(dto.ville());
        existante.setAdresse(dto.adresse());
        existante.setTelephone(dto.telephone());

        Agence updated = agenceRepository.save(existante);

        return agenceMapper.toDTO(updated);
    }

    @Override
    public void deleteById(Long id) {

        if (!agenceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Agence", id);
        }

        agenceRepository.deleteById(id);
    }
}
