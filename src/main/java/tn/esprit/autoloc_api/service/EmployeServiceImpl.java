
package tn.esprit.autoloc_api.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.autoloc_api.domain.Agence;
import tn.esprit.autoloc_api.domain.Employe;
import tn.esprit.autoloc_api.exception.ResourceNotFoundException;
import tn.esprit.autoloc_api.mapper.EmployeMapper;
import tn.esprit.autoloc_api.repository.IAgenceRepository;
import tn.esprit.autoloc_api.repository.IEmployeRepository;
import tn.esprit.autoloc_api.web.dto.EmployeRequestDTO;
import tn.esprit.autoloc_api.web.dto.EmployeResponseDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeServiceImpl implements IEmployeService {

    private final IEmployeRepository employeRepository;
    private final IAgenceRepository agenceRepository;
    private final EmployeMapper employeMapper;

    @Override
    @Transactional
    public EmployeResponseDTO create(EmployeRequestDTO dto) {

        Employe employe = employeMapper.toEntity(dto);

        employe.setAgence(trouverAgence(dto.idAgence()));

        Employe saved = employeRepository.save(employe);

        return employeMapper.toDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeResponseDTO findById(Long id) {

        Employe employe = rechercherEmploye(id);

        return employeMapper.toDTO(employe);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeResponseDTO> findAll() {

        return employeRepository.findAll()
                .stream()
                .map(employeMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional
    public EmployeResponseDTO update(
            Long id, EmployeRequestDTO dto) {

        Employe existant = rechercherEmploye(id);

        existant.setNom(dto.nom());
        existant.setPrenom(dto.prenom());
        existant.setRole(dto.role());
        existant.setAgence(trouverAgence(dto.idAgence()));

        Employe updated = employeRepository.save(existant);

        return employeMapper.toDTO(updated);
    }

    @Override
    public void deleteById(Long id) {

        if (!employeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Employé", id);
        }

        employeRepository.deleteById(id);
    }

    private Employe rechercherEmploye(Long id) {

        return employeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employé", id)
                );
    }

    private Agence trouverAgence(Long idAgence) {

        if (idAgence == null) {
            return null;
        }

        return agenceRepository.findById(idAgence)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Agence", idAgence)
                );
    }
}
