
package tn.esprit.autoloc_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import tn.esprit.autoloc_api.domain.Contrat;
import tn.esprit.autoloc_api.web.dto.ContratRequestDTO;
import tn.esprit.autoloc_api.web.dto.ContratResponseDTO;
import tn.esprit.autoloc_api.exception.ResourceNotFoundException;
import tn.esprit.autoloc_api.mapper.ContratMapper;
import tn.esprit.autoloc_api.repository.IContratRepository;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ContratServiceImpl implements IContratService {

    private final IContratRepository contratRepository;
    private final ContratMapper contratMapper;

    @Override
    public ContratResponseDTO create(ContratRequestDTO dto) {

        Contrat contrat = contratMapper.toEntity(dto);

        Contrat savedContrat = contratRepository.save(contrat);

        return contratMapper.toDTO(savedContrat);
    }

    @Override
    public ContratResponseDTO findById(Long id) {

        Contrat contrat = contratRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Contrat", id)
                );

        return contratMapper.toDTO(contrat);
    }

    @Override
    public List<ContratResponseDTO> findAll() {

        return StreamSupport
                .stream(contratRepository.findAll().spliterator(), false)
                .map(contratMapper::toDTO)
                .toList();
    }

    @Override
    public ContratResponseDTO update(
            Long id,
            ContratRequestDTO dto) {

        Contrat existant = contratRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Contrat", id)
                );

        existant.setDateSignature(dto.dateSignature());
        existant.setMontantTotal(dto.montantTotal());
        existant.setValide(dto.valide());

        Contrat updatedContrat = contratRepository.save(existant);

        return contratMapper.toDTO(updatedContrat);
    }

    @Override
    public void deleteById(Long id) {

        if (!contratRepository.existsById(id)) {
            throw new ResourceNotFoundException("Contrat", id);
        }

        contratRepository.deleteById(id);
    }
}
