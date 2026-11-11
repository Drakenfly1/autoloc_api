
package tn.esprit.autoloc_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import tn.esprit.autoloc_api.domain.Equipement;
import tn.esprit.autoloc_api.exception.ResourceNotFoundException;
import tn.esprit.autoloc_api.mapper.EquipementMapper;
import tn.esprit.autoloc_api.repository.IEquipementRepository;
import tn.esprit.autoloc_api.web.dto.EquipementRequestDTO;
import tn.esprit.autoloc_api.web.dto.EquipementResponseDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipementServiceImpl implements IEquipementService {

    private final IEquipementRepository equipementRepository;
    private final EquipementMapper equipementMapper;

    @Override
    public EquipementResponseDTO create(EquipementRequestDTO dto) {

        Equipement equipement = equipementMapper.toEntity(dto);

        Equipement saved = equipementRepository.save(equipement);

        return equipementMapper.toDTO(saved);
    }

    @Override
    public EquipementResponseDTO findById(Long id) {

        Equipement equipement = equipementRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Equipement", id)
                );

        return equipementMapper.toDTO(equipement);
    }

    @Override
    public List<EquipementResponseDTO> findAll() {

        return equipementRepository.findAll()
                .stream()
                .map(equipementMapper::toDTO)
                .toList();
    }

    @Override
    public EquipementResponseDTO update(
            Long id, EquipementRequestDTO dto) {

        Equipement existant = equipementRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Equipement", id)
                );

        existant.setLibelle(dto.libelle());

        Equipement updated = equipementRepository.save(existant);

        return equipementMapper.toDTO(updated);
    }

    @Override
    public void deleteById(Long id) {

        if (!equipementRepository.existsById(id)) {
            throw new ResourceNotFoundException("Equipement", id);
        }

        equipementRepository.deleteById(id);
    }
}
