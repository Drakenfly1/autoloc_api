
package tn.esprit.autoloc_api.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.autoloc_api.domain.Paiement;
import tn.esprit.autoloc_api.exception.ResourceNotFoundException;
import tn.esprit.autoloc_api.mapper.PaiementMapper;
import tn.esprit.autoloc_api.repository.IPaiementRepository;
import tn.esprit.autoloc_api.web.dto.PaiementResponseDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaiementServiceImpl implements IPaiementService {

    private final IPaiementRepository paiementRepository;
    private final PaiementMapper paiementMapper;

    @Override
    @Transactional(readOnly = true)
    public PaiementResponseDTO findById(Long id) {

        Paiement paiement = paiementRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Paiement", id)
                );

        return paiementMapper.toDTO(paiement);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaiementResponseDTO> findAll() {

        return paiementRepository.findAll()
                .stream()
                .map(paiementMapper::toDTO)
                .toList();
    }
}
